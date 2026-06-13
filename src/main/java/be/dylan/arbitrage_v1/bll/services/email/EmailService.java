package be.dylan.arbitrage_v1.bll.services.email;

import java.io.File;
import java.util.List;

public interface EmailService {
    void sendInvitationEmail(String to, String token);
    void sendConvocationEmail(String to, String arbitreName, String competitionName, String date, String token, String subject, String message, String introMessage, List<File> attachments);
}