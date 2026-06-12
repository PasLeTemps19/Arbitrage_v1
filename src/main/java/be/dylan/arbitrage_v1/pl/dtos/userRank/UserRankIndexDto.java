package be.dylan.arbitrage_v1.pl.dtos.userRank;

public record UserRankIndexDto(
        Long userId,
        String rankStyle,
        String rankType
) {}
