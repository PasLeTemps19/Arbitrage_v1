package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Rank;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankDetailsDto;

public class UserRankMapper {

    public static UserRank convertToUserRank(User user, Rank rank, UserRankCreateFormDto dto) {
        UserRank userRank = new UserRank();
        userRank.setUser(user);
        userRank.setRank(rank);
        userRank.setObtentionDate(dto.getObtentionDate());
        userRank.setLastActive(true);
        return userRank;
    }

    public static UserRankDetailsDto convertToUserRankDetailsDto(UserRank userRank) {
        return new UserRankDetailsDto(
                userRank.getId(),
                userRank.getUser().getId(),
                userRank.getRank().getId(),
                userRank.getObtentionDate(),
                userRank.isLastActive()
        );
    }
}