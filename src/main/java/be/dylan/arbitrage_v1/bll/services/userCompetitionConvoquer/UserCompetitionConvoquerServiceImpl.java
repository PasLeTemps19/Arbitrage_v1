package be.dylan.arbitrage_v1.bll.services.userCompetitionConvoquer;

import be.dylan.arbitrage_v1.bll.mappers.UserCompetitionConvoquerMapper;
import be.dylan.arbitrage_v1.bll.services.competition.CompetitionService;
import be.dylan.arbitrage_v1.bll.services.user.UserService;
import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.dal.repositories.UserCompetitionConvoquerRepository;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCompetitionConvoquerServiceImpl implements UserCompetitionConvoquerService {
    private final UserCompetitionConvoquerRepository userCompetitionConvoquerRepository;
    private final UserService userService;
    private  final CompetitionService competitionService;

    public UserCompetitionConvoquerServiceImpl(UserCompetitionConvoquerRepository userCompetitionConvoquerRepository, UserService userService, CompetitionService competitionService) {
        this.userCompetitionConvoquerRepository = userCompetitionConvoquerRepository;
        this.userService = userService;
        this.competitionService = competitionService;
    }


    @Override
    public UserCompetitionConvoquer addConvocation(UserCompetitionConvoquerCreateFormDto dto) {
        User user = userService.getByIdUser(dto.getUserId());
        Competition competition = competitionService.getCompetitionById(dto.getCompetitionId());
        UserCompetitionConvoquer userCompetitionConvoquer = UserCompetitionConvoquerMapper.convertToUserCompetitionConvoquer(user, competition, dto);
        return userCompetitionConvoquerRepository.save(userCompetitionConvoquer);
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
}
