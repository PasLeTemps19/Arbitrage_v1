package be.dylan.arbitrage_v1.bll.services.userCompetitionConvoquer;

import be.dylan.arbitrage_v1.bll.mappers.UserCompetitionConvoquerMapper;
import be.dylan.arbitrage_v1.bll.services.competition.CompetitionService;
import be.dylan.arbitrage_v1.bll.services.email.EmailService;
import be.dylan.arbitrage_v1.bll.services.user.UserService;
import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.dal.enums.ConvocationStatus;
import be.dylan.arbitrage_v1.dal.repositories.UserCompetitionConvoquerRepository;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserCompetitionConvoquerServiceImpl implements UserCompetitionConvoquerService {
    private final UserCompetitionConvoquerRepository userCompetitionConvoquerRepository;
    private final UserService userService;
    private final CompetitionService competitionService;
    private final EmailService emailService;

    public UserCompetitionConvoquerServiceImpl(
            UserCompetitionConvoquerRepository userCompetitionConvoquerRepository,
            UserService userService,
            CompetitionService competitionService,
            EmailService emailService) {
        this.userCompetitionConvoquerRepository = userCompetitionConvoquerRepository;
        this.userService = userService;
        this.competitionService = competitionService;
        this.emailService = emailService;
    }

    @Override
    public UserCompetitionConvoquer addConvocation(UserCompetitionConvoquerCreateFormDto dto, List<File> attachments) {
        User user = userService.getByIdUser(dto.getUserId());
        Competition competition = competitionService.getCompetitionById(dto.getCompetitionId());
        UserCompetitionConvoquer convoquer = UserCompetitionConvoquerMapper.convertToUserCompetitionConvoquer(user, competition, dto);
        UserCompetitionConvoquer saved = userCompetitionConvoquerRepository.save(convoquer);

        emailService.sendConvocationEmail(
                user.getEmail(),
                user.getName() + " " + user.getSurname(),
                competition.getName(),
                competition.getDate().toString(),
                competition.getTime().toString(),
                competition.getGymnasium().getCity(),
                saved.getToken(),
                dto.getSubject(),
                dto.getMessage(),
                dto.getIntroMessage(),
                attachments
        );

        return saved;
    }

    @Override
    public void respondToConvocation(String token, String response) {
        UserCompetitionConvoquer convoquer = userCompetitionConvoquerRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide"));

        if (convoquer.isTokenUsed()) {
            throw new RuntimeException("Ce lien a déjà été utilisé");
        }

        convoquer.setStatus(ConvocationStatus.valueOf(response));
        convoquer.setTokenUsed(true);
        userCompetitionConvoquerRepository.save(convoquer);
    }

    @Override
    public void updateStatus(Long userId, Long competitionId, ConvocationStatus status) {
        UserCompetitionConvoquer.UserCompetitionConvoquerId id =
                new UserCompetitionConvoquer.UserCompetitionConvoquerId(userId, competitionId);
        UserCompetitionConvoquer convoquer = userCompetitionConvoquerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convocation non trouvée"));
        convoquer.setStatus(status);
        userCompetitionConvoquerRepository.save(convoquer);
    }

    @Override
    public List<UserCompetitionConvoquer> getConvocationsByUser(Long userId) {
        User user = userService.getByIdUser(userId);
        return userCompetitionConvoquerRepository.findByUser(user);
    }

    @Override
    public List<UserCompetitionConvoquer> getConvocationsByCompetition(Long competitionId) {
        Competition competition = competitionService.getCompetitionById(competitionId);
        return userCompetitionConvoquerRepository.findByCompetition(competition);
    }

    @Override
    public void deleteConvocation(Long userId, Long competitionId) {
        UserCompetitionConvoquer.UserCompetitionConvoquerId id =
                new UserCompetitionConvoquer.UserCompetitionConvoquerId(userId, competitionId);
        userCompetitionConvoquerRepository.deleteById(id);
    }
    @Override
    public void trackEmailOpen(String token) {
        userCompetitionConvoquerRepository.findByToken(token).ifPresent(c -> {
            if (!c.isEmailOpened()) {
                c.setEmailOpened(true);
                c.setEmailOpenedAt(LocalDateTime.now());
                userCompetitionConvoquerRepository.save(c);
            }
        });
    }
}