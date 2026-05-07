package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank, Long> {
}
