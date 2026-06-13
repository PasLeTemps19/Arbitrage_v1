package be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer;

import java.time.LocalDate;

public record UserCompetitionConvoquerDetailsDto(
        Long userId,
        Long competitionId,
        LocalDate convocDate,
        String userName,
        String competitionName,
        String status
) {}