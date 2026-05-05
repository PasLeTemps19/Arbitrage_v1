package be.dylan.arbitrage_v1.bll.services.user;

import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.pl.dtos.user.UserCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserUpdateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserUpdatePasswordFormDto;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    User getByIdUser(Long id);
    User addUser(UserCreateFormDto userCreateFormDto);
    User updateUser(Long id, UserUpdateFormDto userUpdateFormDto);
    void deleteUser(Long id);
    void updatePassword(Long id, UserUpdatePasswordFormDto  userUpdatePasswordFormDto);
}
