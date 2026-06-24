package be.dylan.arbitrage_v1.dal.repositories;



import be.dylan.arbitrage_v1.dal.entities.Rank;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserRank;
import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRankRepository extends JpaRepository<UserRank, Long> {
    List<UserRank> findByUser(User user);
    Optional<UserRank> findByUserAndRank_StyleAndLastActiveTrue(User user, RankStyle style);
    boolean existsByUserAndRankAndLastActive(User user, Rank rank, boolean lastActive);
    Optional<UserRank> findByUserAndRank_StyleAndLastActive(User user, RankStyle style, boolean lastActive);
    List<UserRank> findByLastActiveTrue();
    boolean existsByUserAndRank(User user, Rank rank);
}
