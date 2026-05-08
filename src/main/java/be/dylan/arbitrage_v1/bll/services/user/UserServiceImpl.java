package be.dylan.arbitrage_v1.bll.services.user;

import be.dylan.arbitrage_v1.bll.mappers.UserMapper;
import be.dylan.arbitrage_v1.bll.services.email.EmailService;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.repositories.UserRepository;
import be.dylan.arbitrage_v1.pl.dtos.user.UserCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserUpdateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserUpdatePasswordFormDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;

    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByIdUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addUser(UserCreateFormDto userCreateFormDto) {
        if(!userCreateFormDto.getPassword().equals(userCreateFormDto.getConfirmPassword())) {
            throw new IllegalArgumentException("les mots de passe ne correspondent pas");

        }
        User user = UserMapper.convertToUser(userCreateFormDto);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserUpdateFormDto userUpdateFormDto) {
        User user =getByIdUser(id);
    User userUpdate = UserMapper.convertUpdateToUser(user, userUpdateFormDto);
        return userRepository.save(userUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getByIdUser(id);
        userRepository.delete(user);
    }

    @Override
    public void updatePassword(Long id, UserUpdatePasswordFormDto userUpdatePasswordFormDto) {
        User user = getByIdUser(id);

        if (!user.getPassword().equals(userUpdatePasswordFormDto.getOldPassword())) {
            throw new IllegalArgumentException("L'ancien mot de passe est incorrect.");
        }
        if (!userUpdatePasswordFormDto.getPassword().equals(userUpdatePasswordFormDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Les nouveaux mots de passe ne correspondent pas.");
        }
        user.setPassword(userUpdatePasswordFormDto.getPassword());
         userRepository.save(user);
    }

    @Override
    public void inviteUser(String email) {
        // 1. Générer un token unique
        String token = UUID.randomUUID().toString();

        // 2. Créer un user avec juste l'email et le token
        User user = new User();
        user.setEmail(email);
        user.setToken(token);
        user.setActive(false); // inactif jusqu'à l'inscription
        userRepository.save(user);

        // 3. Envoyer l'email d'invitation
        emailService.sendInvitationEmail(email, token);
    }

    @Override
    public void registerUser(String token) {
        // 1. Vérifier que le token existe
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide ou expiré"));

        // 2. Vérifier que le user n'est pas déjà inscrit
        if(user.getName() != null) {
            throw new RuntimeException("Un compte existe déjà avec cet email");
        }

        // 3. Activer le user
        user.setActive(true);
        userRepository.save(user);
    }


}
