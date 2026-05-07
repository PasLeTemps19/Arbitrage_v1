package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerDetailsDto;

public class UserCompetitionConvoquerMapper {

    public static UserCompetitionConvoquer convertToUserCompetitionConvoquer(User user, Competition competition, UserCompetitionConvoquerCreateFormDto userCompetitionConvoquerCreateFormDto){
        UserCompetitionConvoquer userCompetitionConvoquer = new UserCompetitionConvoquer();
        UserCompetitionConvoquer.UserCompetitionConvoquerId id = new UserCompetitionConvoquer.UserCompetitionConvoquerId(user.getId(), competition.getId());
        userCompetitionConvoquer.setId(id);
        userCompetitionConvoquer.setUser(user);
        userCompetitionConvoquer.setCompetition(competition);
        userCompetitionConvoquer.setConvocDate(userCompetitionConvoquerCreateFormDto.getConvocDate());
        return userCompetitionConvoquer;
    }

    public static UserCompetitionConvoquerDetailsDto convertToUserCompetitionConvoquerDetailsDto (UserCompetitionConvoquer userCompetitionConvoquer){
        return new  UserCompetitionConvoquerDetailsDto(
                userCompetitionConvoquer.getUser().getId(),
                userCompetitionConvoquer.getCompetition().getId(),
                userCompetitionConvoquer.getConvocDate()
        );
    }

}
