package be.dylan.arbitrage_v1.dal.repositories;

import be.dylan.arbitrage_v1.dal.entities.Competition;
import be.dylan.arbitrage_v1.dal.entities.ScheduledEmail;
import be.dylan.arbitrage_v1.dal.enums.ScheduledEmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledEmailRepository extends JpaRepository<ScheduledEmail, Long> {
    List<ScheduledEmail> findByStatusAndSendAtBefore(ScheduledEmailStatus status, LocalDateTime now);
    List<ScheduledEmail> findByCompetition(Competition competition);
}