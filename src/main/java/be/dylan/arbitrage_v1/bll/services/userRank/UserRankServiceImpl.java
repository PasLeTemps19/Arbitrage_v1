package be.dylan.arbitrage_v1.bll.services.userRank;


import be.dylan.arbitrage_v1.bll.mappers.UserRankMapper;
import be.dylan.arbitrage_v1.bll.services.user.UserService;
import be.dylan.arbitrage_v1.dal.entities.Rank;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import be.dylan.arbitrage_v1.dal.repositories.RankRepository;
import be.dylan.arbitrage_v1.dal.repositories.UserRankRepository;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRankServiceImpl implements UserRankService {

    private final UserRankRepository userRankRepository;
    private final UserService userService;
    private final RankRepository rankRepository;

    public UserRankServiceImpl(UserRankRepository userRankRepository, UserService userService, RankRepository rankRepository) {
        this.userRankRepository = userRankRepository;
        this.userService = userService;
        this.rankRepository = rankRepository;
    }


    @Override
    public UserRank assignRank(UserRankCreateFormDto userRankCreateFormDto) {
        User user = userService.getByIdUser(userRankCreateFormDto.getUserId());
        Rank rank = rankRepository.findById(userRankCreateFormDto.getRankId()).orElseThrow(()-> new RuntimeException("Rank pas trouvé"));
        userRankRepository.findByUserAndRank_StyleAndLastActiveTrue(user,rank.getStyle()).ifPresent(oldRank ->{oldRank.setLastActive(false);
        userRankRepository.save(oldRank);});
        UserRank userRank = UserRankMapper.convertToUserRank(user,rank,userRankCreateFormDto);
        return userRankRepository.save(userRank);
    }

    @Override
    public List<UserRank> getUserRanks(Long userId) {
        User user = userService.getByIdUser(userId);
        return userRankRepository.findByUser(user);

    }
}