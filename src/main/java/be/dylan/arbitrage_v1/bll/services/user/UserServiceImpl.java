package be.dylan.arbitrage_v1.bll.services.user;

import be.dylan.arbitrage_v1.bll.mappers.UserMapper;
import be.dylan.arbitrage_v1.bll.services.email.EmailService;
import be.dylan.arbitrage_v1.bll.services.userRank.UserRankService;
import be.dylan.arbitrage_v1.dal.entities.Rank;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.repositories.RankRepository;
import be.dylan.arbitrage_v1.dal.repositories.UserRepository;
import be.dylan.arbitrage_v1.pl.dtos.user.UserCompleteProfileFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.user.UserUpdateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserRankService userRankService;
    private final RankRepository rankRepository;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService,@Lazy UserRankService userRankService, RankRepository rankRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userRankService = userRankService;
        this.rankRepository = rankRepository;

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
    public void inviteUser(String email) {
        // 1. Générer un token unique
        String token = UUID.randomUUID().toString();


        User user = new User();
        user.setEmail(email);
        user.setToken(token);
        user.setActive(false);
        userRepository.save(user);

        // 2. Envoyer l'email d'invitation
        emailService.sendInvitationEmail(email, token);
    }

    @Override
    public void registerUser(String token) {

        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide ou expiré"));


        if(user.getName() != null) {
            throw new RuntimeException("Un compte existe déjà avec cet email");
        }


        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void completeProfile(UserCompleteProfileFormDto dto, Authentication authentication) {
        // 1. Récupérer les infos du token Keycloak
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("given_name");
        String surname = jwt.getClaim("family_name");
        UUID keycloakId = UUID.fromString(jwt.getSubject());

        // 2. Trouver le user en base via email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User non trouvé"));

        // 3. Vérifier que les styles sont différents
        if(dto.getRankStyle1().equals(dto.getRankStyle2())) {
            throw new IllegalArgumentException("Les deux styles ne peuvent pas être identiques.");
        }

        // 4. Mettre à jour le profil
        UserMapper.convertCompleteProfile(user, dto, name, surname);
        user.setKeycloakId(keycloakId);
        userRepository.save(user);

        // 5. Assigner les deux rangs
        Rank rank1 = rankRepository.findByStyleAndType(dto.getRankStyle1(), dto.getRankType1())
                .orElseThrow(() -> new RuntimeException("Rang 1 non trouvé"));
        Rank rank2 = rankRepository.findByStyleAndType(dto.getRankStyle2(), dto.getRankType2())
                .orElseThrow(() -> new RuntimeException("Rang 2 non trouvé"));

        UserRankCreateFormDto userRankDto1 = new UserRankCreateFormDto();
        userRankDto1.setUserId(user.getId());
        userRankDto1.setRankId(rank1.getId());

        UserRankCreateFormDto userRankDto2 = new UserRankCreateFormDto();
        userRankDto2.setUserId(user.getId());
        userRankDto2.setRankId(rank2.getId());

        userRankService.assignRank(userRankDto1);
        userRankService.assignRank(userRankDto2);
    }


}
