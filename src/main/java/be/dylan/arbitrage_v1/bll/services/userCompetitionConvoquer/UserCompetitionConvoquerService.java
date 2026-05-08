package be.dylan.arbitrage_v1.bll.services.userCompetitionConvoquer;

import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer.UserCompetitionConvoquerCreateFormDto;

import java.util.List;

public interface UserCompetitionConvoquerService {
    UserCompetitionConvoquer addConvocation(UserCompetitionConvoquerCreateFormDto dto);
    List<UserCompetitionConvoquer> getConvocationsByUser(Long userId);
    List<UserCompetitionConvoquer> getConvocationsByCompetition(Long competitionId);
    void deleteConvocation(Long userId, Long competitionId);
}