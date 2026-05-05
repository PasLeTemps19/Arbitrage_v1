package be.dylan.arbitrage_v1.pl.dtos.competition;

import java.time.LocalDate;


public record CompetitionIndexDto(
        Long id,
        String name,
        LocalDate date
) {}
