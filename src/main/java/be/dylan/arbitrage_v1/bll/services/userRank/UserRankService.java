package be.dylan.arbitrage_v1.bll.services.userRank;

import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;

import java.util.List;

public interface UserRankService {
    UserRank assignRank(UserRankCreateFormDto userRankCreateFormDto);
    List<UserRank> getUserRanks(Long userId);
}