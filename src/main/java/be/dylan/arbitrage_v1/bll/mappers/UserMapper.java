package be.dylan.arbitrage_v1.bll.mappers;

import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.pl.dtos.user.*;

public class UserMapper {

    public static UserIndexDto convertToUserIndexDto(User user) {
        return new UserIndexDto(
                user.getId(),
                user.getName()
        );
    }

    public static UserDetailsUserViewDto convertToUserDetailsUserViewDto(User user) {
        return new UserDetailsUserViewDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getClub(),
                user.getEmail()
        );
    }


    public static User convertToUser(UserCreateFormDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setClub(dto.getClub());
        user.setEmail(dto.getEmail());
        user.setActive(true);
        return user;
    }

    public static User convertUpdateToUser(User user, UserUpdateFormDto dto) {
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setClub(dto.getClub());
        user.setEmail(dto.getEmail());
        return user;
    }


}
