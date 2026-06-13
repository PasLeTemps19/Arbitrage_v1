package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCompetitionConvoquerRepository extends JpaRepository<UserCompetitionConvoquer, UserCompetitionConvoquer.UserCompetitionConvoquerId> {
    List<UserCompetitionConvoquer> findByUser(User user);
    List<UserCompetitionConvoquer> findByCompetition(Competition competition);
    Optional<UserCompetitionConvoquer> findByToken(String token);
}