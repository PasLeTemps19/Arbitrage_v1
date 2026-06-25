package be.dylan.arbitrage_v1.pl.dtos.user;

import be.dylan.arbitrage_v1.dal.enums.UserType;

public record UserIndexDto(
        Long id,
        String name,
        String surname,
        String phoneNumber,
        String email,
        boolean pending,
        UserType userType
) {

}
