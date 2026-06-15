package be.dylan.arbitrage_v1.bll.services.scheduledEmail;

import be.dylan.arbitrage_v1.dal.entities.ScheduledEmail;
import be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail.ScheduledEmailCreateFormDto;


import java.util.List;

public interface ScheduledEmailService {
    ScheduledEmail create(ScheduledEmailCreateFormDto dto);
    List<ScheduledEmail> getByCompetition(Long competitionId);
    void delete(Long id);
    void processPendingEmails();
}