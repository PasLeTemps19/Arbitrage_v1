package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
