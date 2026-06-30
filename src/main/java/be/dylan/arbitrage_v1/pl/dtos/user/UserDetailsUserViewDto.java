package be.dylan.arbitrage_v1.pl.dtos.user;

import be.dylan.arbitrage_v1.dal.enums.UserType;

import java.time.LocalDate;

public record UserDetailsUserViewDto(
        Long id,
        String name,
        String surname,
        String club,
        String email,
        String grade,
        LocalDate birthDate,
        String street,
        Integer number,
        String postalCode,
        String city,
        String phoneNumber,
        String phoneNumberTwo,
        String iban,
        boolean active,
        LocalDate createDate,
        LocalDate updateDate,
        String department,
        UserType userType


) {
}
