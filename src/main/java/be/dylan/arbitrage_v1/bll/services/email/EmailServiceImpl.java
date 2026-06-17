package be.dylan.arbitrage_v1.bll.services.email;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final Configuration freeMarkerConfig;

    @Value("${spring.mail.from}")
    private String from;

    public EmailServiceImpl(JavaMailSender mailSender, FreeMarkerConfig freeMarkerConfig) {
        this.mailSender = mailSender;
        this.freeMarkerConfig = freeMarkerConfig.getConfiguration(); // ← fix ici
    }

    @Override
    public void sendInvitationEmail(String to, String token) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Invitation à rejoindre Arbitrage");

            // ← fix ici : récupérer le template puis le traiter
            var template = freeMarkerConfig.getTemplate("email/invitation.ftlh");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(
                    template,
                    Map.of("token", token)
            );

            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }

    @Override
    public void sendConvocationEmail(String to, String arbitreName, String competitionName, String date,String heure, String lieu, String token, String subject, String message, String introMessage, List<File> attachments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject != null ? subject : "Convocation - " + competitionName);

            var template = freeMarkerConfig.getTemplate("email/convocation.ftlh");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(
                    template,
                    Map.of(
                            "arbitreName", arbitreName,
                            "competitionName", competitionName,
                            "date", date,
                            "heure", heure,
                            "lieu", lieu,
                            "introMessage", introMessage != null ? introMessage : "Vous êtes convoqué(e) en tant qu'arbitre pour la compétition suivante :",
                            "message", message != null ? message : "",
                            "acceptUrl", "http://localhost:8080/convocation/respond/" + token + "?response=ACCEPTE",
                            "refuseUrl", "http://localhost:8080/convocation/respond/" + token + "?response=REFUSE"
                    )
            );

            helper.setText(html, true);

            if (attachments != null) {
                for (File attachment : attachments) {
                    helper.addAttachment(attachment.getName(), attachment);
                }
            }

            mailSender.send(mimeMessage);

        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException("Erreur lors de l'envoi de l'email", e);
        }
    }


}