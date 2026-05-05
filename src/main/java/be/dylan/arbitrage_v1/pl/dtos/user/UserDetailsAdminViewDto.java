package be.dylan.arbitrage_v1.pl.dtos.user;

import java.time.LocalDate;

public record UserDetailsAdminViewDto(
        Long id,
        String name,
        String surname,
        String club,
        String email,
        boolean active,
        LocalDate createDate,
        LocalDate updateDate
        ) {
}
