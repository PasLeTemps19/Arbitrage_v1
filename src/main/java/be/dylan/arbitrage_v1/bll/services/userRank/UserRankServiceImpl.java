package be.dylan.arbitrage_v1.bll.services.userRank;


import be.dylan.arbitrage_v1.bll.mappers.UserRankMapper;
import be.dylan.arbitrage_v1.bll.services.user.UserService;
import be.dylan.arbitrage_v1.dal.entities.Rank;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.dal.repositories.RankRepository;
import be.dylan.arbitrage_v1.dal.repositories.UserRankRepository;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankCreateFormDto;
import be.dylan.arbitrage_v1.pl.dtos.userRank.UserRankPromoteFormDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class UserRankServiceImpl implements UserRankService {

    private final UserRankRepository userRankRepository;
    private final UserService userService;
    private final RankRepository rankRepository;

    public UserRankServiceImpl(UserRankRepository userRankRepository, UserService userService, RankRepository rankRepository) {
        this.userRankRepository = userRankRepository;
        this.userService = userService;
        this.rankRepository = rankRepository;
    }


    @Override
    public UserRank assignRank(UserRankCreateFormDto userRankCreateFormDto) {
        User user = userService.getByIdUser(userRankCreateFormDto.getUserId());
        Rank rank = rankRepository.findById(userRankCreateFormDto.getRankId()).orElseThrow(()-> new RuntimeException("Rank pas trouvé"));
        userRankRepository.findByUserAndRank_StyleAndLastActiveTrue(user,rank.getStyle()).ifPresent(oldRank ->{oldRank.setLastActive(false);
        userRankRepository.save(oldRank);});
        UserRank userRank = UserRankMapper.convertToUserRank(user,rank,userRankCreateFormDto);
        return userRankRepository.save(userRank);
    }

    @Override
    public List<UserRank> getUserRanks(Long userId) {
        User user = userService.getByIdUser(userId);
        return userRankRepository.findByUser(user);

    }

    @Override
    public void assignRankIfChanged(User user, Rank rank, LocalDate obtentionDate) {
        // Cherche si ce rank exact est déjà actif pour cet utilisateur
        boolean alreadyActive = userRankRepository
                .existsByUserAndRankAndLastActive(user, rank, true);

        if (!alreadyActive) {
            // Désactive l'ancien rank actif du même style
            userRankRepository.findByUserAndRank_StyleAndLastActive(user, rank.getStyle(), true)
                    .ifPresent(old -> {
                        old.setLastActive(false);
                        userRankRepository.save(old);
                    });

            // Crée le nouveau rank actif
            UserRank userRank = new UserRank();
            userRank.setUser(user);
            userRank.setRank(rank);
            userRank.setObtentionDate(obtentionDate);
            userRank.setLastActive(true);
            userRankRepository.save(userRank);
        }
    }

    @Override
    public List<UserRank> getAllActiveRanks() {
        return userRankRepository.findByLastActiveTrue();
    }

    @Override
    public UserRank promoteRank(UserRankPromoteFormDto dto) {
        User user = userService.getByIdUser(dto.getUserId());
        Rank rank = rankRepository.findByStyleAndType(dto.getRankStyle(), dto.getRankType())
                .orElseThrow(() -> new RuntimeException("Rang non trouvé"));

        // Vérifier si ce rang exact est déjà actif
        boolean alreadyActive = userRankRepository
                .existsByUserAndRankAndLastActive(user, rank, true);
        if (alreadyActive) {
            throw new RuntimeException("Ce rang est déjà le rang actif de cet arbitre.");
        }

        // Vérifier si ce rang existe déjà dans l'historique
        boolean alreadyExists = userRankRepository
                .existsByUserAndRank(user, rank);
        if (alreadyExists) {
            throw new RuntimeException("Ce rang a déjà été assigné à cet arbitre par le passé.");
        }

        userRankRepository.findByUserAndRank_StyleAndLastActiveTrue(user, rank.getStyle())
                .ifPresent(old -> {
                    old.setLastActive(false);
                    userRankRepository.save(old);
                });

        UserRank userRank = new UserRank();
        userRank.setUser(user);
        userRank.setRank(rank);
        userRank.setObtentionDate(dto.getObtentionDate());
        userRank.setLastActive(true);
        return userRankRepository.save(userRank);
    }

    @Override
    public void deleteRankUser(Long id) {
        userRankRepository.deleteById(id);
    }
}