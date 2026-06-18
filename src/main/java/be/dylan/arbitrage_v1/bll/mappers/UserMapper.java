package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.pl.dtos.user.*;

public class UserMapper {

    public static UserIndexDto convertToUserIndexDto(User user) {
        return new UserIndexDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getToken() != null
        );
    }

    public static UserDetailsUserViewDto convertToUserDetailsUserViewDto(User user) {
        return new UserDetailsUserViewDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getClub(),
                user.getEmail(),
                user.getGrade(),
                user.getBirthDate(),
                user.getStreet(),
                user.getNumber(),
                user.getPostalCode(),
                user.getCity(),
                user.getPhoneNumber(),
                user.getPhoneNumberTwo(),
                user.getIban(),
                user.isActive(),
                user.getCreateDate(),
                user.getUpdateDate()

        );
    }


    public static User convertToUser(UserCreateFormDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setClub(dto.getClub());
        user.setEmail(dto.getEmail());
        user.setGrade(dto.getGrade());
        user.setBirthDate(dto.getBirthDate());
        user.setStreet(dto.getStreet());
        user.setNumber(dto.getNumber());
        user.setPostalCode(dto.getPostalCode());
        user.setCity(dto.getCity());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPhoneNumberTwo(dto.getPhoneNumberTwo());
        user.setIban(dto.getIban());
        user.setActive(true);
        return user;
    }

    public static User convertUpdateToUser(User user, UserUpdateFormDto dto) {
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setClub(dto.getClub());
        user.setEmail(dto.getEmail());
        user.setGrade(dto.getGrade());
        user.setBirthDate(dto.getBirthDate());
        user.setStreet(dto.getStreet());
        user.setNumber(dto.getNumber());
        user.setPostalCode(dto.getPostalCode());
        user.setCity(dto.getCity());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPhoneNumberTwo(dto.getPhoneNumberTwo());
        user.setIban(dto.getIban());
        return user;
    }

    public static User convertCompleteProfile(User user, UserCompleteProfileFormDto dto, String name, String surname) {
        user.setName(name);
        user.setSurname(surname);
        user.setClub(dto.getClub());
        user.setGrade(dto.getGrade());
        user.setBirthDate(dto.getBirthDate());
        user.setStreet(dto.getStreet());
        user.setNumber(dto.getNumber());
        user.setPostalCode(dto.getPostalCode());
        user.setCity(dto.getCity());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPhoneNumberTwo(dto.getPhoneNumberTwo());
        user.setIban(dto.getIban());
        user.setActive(true);
        user.setToken(null);
        return user;
    }


}
