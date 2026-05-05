package be.dylan.arbitrage_v1.pl.dtos.season;

import java.time.LocalDate;

public record SeasonDetailsDto(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate
) {}