package be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer;

import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;

import java.time.LocalDate;

public record UserCompetitionConvoquerDetailsDto(
        Long userId,
        Long competitionId,
        LocalDate convocDate
) {

}
