package be.dylan.arbitrage_v1.dal.entities;

import be.dylan.arbitrage_v1.dal.enums.ConvocationStatus;
import be.dylan.arbitrage_v1.dal.enums.ScheduledEmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime sendAt;

    @Column(nullable = false)
    private String subject;

    @Column(length = 2000)
    private String message;

    @Column(length = 1000)
    private String introMessage;

    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConvocationStatus targetStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduledEmailStatus status = ScheduledEmailStatus.PENDING;
}