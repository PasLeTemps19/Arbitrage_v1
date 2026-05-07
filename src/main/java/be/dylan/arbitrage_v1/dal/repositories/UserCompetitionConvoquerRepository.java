package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.User;
import be.dylan.arbitrage_v1.dal.entities.UserCompetitionConvoquer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompetitionConvoquerRepository extends JpaRepository<UserCompetitionConvoquer, UserCompetitionConvoquer.UserCompetitionConvoquerId> {
    List<UserCompetitionConvoquer> findByUser(User user);
    List<UserCompetitionConvoquer> findByCompetition(Competition competition);
}