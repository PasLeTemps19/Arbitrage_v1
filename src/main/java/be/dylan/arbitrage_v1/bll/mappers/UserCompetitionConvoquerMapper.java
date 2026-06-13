package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.dal.enums.ConvocationStatus;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerDetailsDto;

import java.util.UUID;

public class UserCompetitionConvoquerMapper {

    public static UserCompetitionConvoquer convertToUserCompetitionConvoquer(User user, Competition competition, UserCompetitionConvoquerCreateFormDto dto) {
        UserCompetitionConvoquer convoquer = new UserCompetitionConvoquer();
        UserCompetitionConvoquer.UserCompetitionConvoquerId id = new UserCompetitionConvoquer.UserCompetitionConvoquerId(user.getId(), competition.getId());
        convoquer.setId(id);
        convoquer.setUser(user);
        convoquer.setCompetition(competition);
        convoquer.setConvocDate(java.time.LocalDate.now());
        convoquer.setStatus(ConvocationStatus.EN_ATTENTE);
        convoquer.setToken(UUID.randomUUID().toString());
        convoquer.setTokenUsed(false);
        convoquer.setSubject(dto.getSubject());
        convoquer.setMessage(dto.getMessage());
        convoquer.setIntroMessage(dto.getIntroMessage());
        return convoquer;
    }

    public static UserCompetitionConvoquerDetailsDto convertToUserCompetitionConvoquerDetailsDto(UserCompetitionConvoquer c) {
        return new UserCompetitionConvoquerDetailsDto(
                c.getUser().getId(),
                c.getCompetition().getId(),
                c.getConvocDate(),
                c.getUser().getName() + " " + c.getUser().getSurname(),
                c.getCompetition().getName(),
                c.getStatus().name()
        );
    }
}