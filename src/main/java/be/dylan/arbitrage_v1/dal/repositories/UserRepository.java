package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
