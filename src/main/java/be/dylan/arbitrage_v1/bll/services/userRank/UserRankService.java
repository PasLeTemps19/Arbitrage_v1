package be.dylan.arbitrage_v1.bll.services.userRank;

import be.dylan.arbitrage_v1.dal.entities.Rank;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankPromoteFormDto;

import java.time.LocalDate;
import java.util.List;

public interface UserRankService {
    UserRank assignRank(UserRankCreateFormDto userRankCreateFormDto);
    List<UserRank> getUserRanks(Long userId);
    void assignRankIfChanged(User user, Rank rank, LocalDate obtentionDate);
    List<UserRank> getAllActiveRanks();
    UserRank promoteRank(UserRankPromoteFormDto dto);
}