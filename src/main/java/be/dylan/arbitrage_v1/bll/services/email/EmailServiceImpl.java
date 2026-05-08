package be.dylan.arbitrage_v1.bll.services.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl  implements EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendInvitationEmail(String to, String token){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Invitation à rejoindre Arbitrage");
            helper.setText(
                    "<h2>Vous avez été invité à rejoindre la plateforme Arbitrage</h2>" +
                            "<p>Cliquez sur le lien ci-dessous pour vous inscrire :</p>" +
                            "<a href='http://localhost:8080/users/register/" + token + "' data-mt-no-track>S'inscrire</a>" +
                            "<p>Ce lien est valable 24h.</p>",
                    true
            );
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email");
        }
    }
}
