package be.dylan.arbitrage_v1.dal.repositories;


import be.dylan.arbitrage_v1.dal.entities.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRankRepository  extends JpaRepository<UserRank,Long> {

}
