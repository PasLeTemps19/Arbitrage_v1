package be.dylan.arbitrage_v1.pl.dtos.userRank;

import java.time.LocalDate;

public record UserRankDetailsDto(
        Long id,
        Long userId,
        Long rankId,
        LocalDate obtentionDate,
        boolean lastActive
) {}