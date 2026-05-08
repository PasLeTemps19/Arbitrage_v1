package be.dylan.arbitrage_v1.bll.services.email;

public interface EmailService {
    void sendInvitationEmail(String to, String token);
}