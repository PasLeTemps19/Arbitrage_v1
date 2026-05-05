package be.dylan.arbitrage_v1.pl.dtos.competition;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionDetailsDto(
        Long id,
        String name,
        LocalDate date,
        LocalTime time,
        String description,
        Long seasonId,
        String seasonName,
        Long gymnasiumId,
        String gymnasiumName
) {}
