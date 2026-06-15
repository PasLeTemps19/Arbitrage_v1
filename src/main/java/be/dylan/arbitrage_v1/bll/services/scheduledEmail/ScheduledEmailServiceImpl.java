package be.dylan.arbitrage_v1.bll.services.scheduledEmail;

import be.dylan.arbitrage_v1.bll.mappers.ScheduledEmailMapper;
import be.dylan.arbitrage_v1.bll.services.competition.CompetitionService;
import be.dylan.arbitrage_v1.bll.services.email.EmailService;
import be.dylan.arbitrage_v1.bll.services.userCompetitionConvoquer.UserCompetitionConvoquerService;
import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.ScheduledEmail;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.dal.enums.ScheduledEmailStatus;
import be.dylan.arbitrage_v1.dal.repositories.ScheduledEmailRepository;
import be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail.ScheduledEmailCreateFormDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduledEmailServiceImpl implements ScheduledEmailService {

    private final ScheduledEmailRepository scheduledEmailRepository;
    private final CompetitionService competitionService;
    private final UserCompetitionConvoquerService userCompetitionConvoquerService;
    private final EmailService emailService;

    public ScheduledEmailServiceImpl(
            ScheduledEmailRepository scheduledEmailRepository,
            CompetitionService competitionService,
            UserCompetitionConvoquerService userCompetitionConvoquerService,
            EmailService emailService) {
        this.scheduledEmailRepository = scheduledEmailRepository;
        this.competitionService = competitionService;
        this.userCompetitionConvoquerService = userCompetitionConvoquerService;
        this.emailService = emailService;
    }

    @Override
    public ScheduledEmail create(ScheduledEmailCreateFormDto dto) {
        Competition competition = competitionService.getCompetitionById(dto.getCompetitionId());
        ScheduledEmail scheduledEmail = ScheduledEmailMapper.convertToScheduledEmail(competition, dto);
        return scheduledEmailRepository.save(scheduledEmail);
    }

    @Override
    public List<ScheduledEmail> getByCompetition(Long competitionId) {
        Competition competition = competitionService.getCompetitionById(competitionId);
        return scheduledEmailRepository.findByCompetition(competition);
    }

    @Override
    public void delete(Long id) {
        scheduledEmailRepository.deleteById(id);
    }
    @Scheduled(fixedRate = 60000)
    @Override
    public void processPendingEmails() {
        List<ScheduledEmail> pending = scheduledEmailRepository
                .findByStatusAndSendAtBefore(ScheduledEmailStatus.PENDING, LocalDateTime.now());

        for (ScheduledEmail scheduled : pending) {
            try {
                List<UserCompetitionConvoquer> convocations = userCompetitionConvoquerService
                        .getConvocationsByCompetition(scheduled.getCompetition().getId());

                for (UserCompetitionConvoquer convoquer : convocations) {
                    if (convoquer.getStatus() == scheduled.getTargetStatus()) {
                        emailService.sendConvocationEmail(
                                convoquer.getUser().getEmail(),
                                convoquer.getUser().getName() + " " + convoquer.getUser().getSurname(),
                                scheduled.getCompetition().getName(),
                                scheduled.getCompetition().getDate().toString(),
                                convoquer.getToken(),
                                scheduled.getSubject(),
                                scheduled.getMessage(),
                                scheduled.getIntroMessage(),
                                null
                        );
                    }
                }

                scheduled.setStatus(ScheduledEmailStatus.SENT);
                scheduledEmailRepository.save(scheduled);

            } catch (Exception e) {
                scheduled.setStatus(ScheduledEmailStatus.FAILED);
                scheduledEmailRepository.save(scheduled);
            }
        }
    }
}