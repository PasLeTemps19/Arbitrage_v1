package be.dylan.arbitrage_v1.bll.services.user;

import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.pl.dtos.user.*;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    User getByIdUser(Long id);
    User addUser(UserCreateFormDto userCreateFormDto);
    User updateUser(Long id, UserUpdateFormDto userUpdateFormDto);
    void deleteUser(Long id);
    void inviteUser(UserInviteFormDto dto);
    void registerUser(String token);
    void completeProfile(UserCompleteProfileFormDto dto, Authentication authentication);
    User getMe(String email);
    void toggleActive(Long id);
    void resendInvitation(Long id);
    User createExternalUser(UserExternalCreateFormDto dto);
    User updateExternalUser(Long id, UserExternalUpdateFormDto dto);
    void deletePendingUser(Long id);
}
