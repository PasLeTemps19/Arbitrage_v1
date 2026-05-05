package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.Gymnasium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymnasiumRepository  extends JpaRepository<Gymnasium,Long> {
}
