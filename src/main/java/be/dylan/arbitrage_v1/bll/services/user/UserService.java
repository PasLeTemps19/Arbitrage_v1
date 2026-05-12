package be.dylan.arbitrage_v1.bll.services.user;

import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.pl.dtos.user.UserCompleteProfileFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserUpdateFormDto;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    User getByIdUser(Long id);
    User addUser(UserCreateFormDto userCreateFormDto);
    User updateUser(Long id, UserUpdateFormDto userUpdateFormDto);
    void deleteUser(Long id);
    void inviteUser(String email);
    void registerUser(String token);
    void completeProfile(UserCompleteProfileFormDto dto, Authentication authentication);
}
