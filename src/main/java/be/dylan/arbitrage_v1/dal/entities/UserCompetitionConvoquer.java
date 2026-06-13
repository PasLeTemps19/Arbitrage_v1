package be.dylan.arbitrage_v1.dal.entities;

import be.dylan.arbitrage_v1.dal.enums.ConvocationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserCompetitionConvoquer implements Serializable {

    @EmbeddedId
    private UserCompetitionConvoquerId id;

    @Column(nullable = false)
    private LocalDate convocDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConvocationStatus status = ConvocationStatus.EN_ATTENTE;

    @Column(unique = true)
    private String token;

    @Column(nullable = false)
    private boolean tokenUsed = false;

    @Column
    private String subject;

    @Column(length = 2000)
    private String message;

    @Column(length = 1000)
    private String introMessage;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("competitionId")
    private Competition competition;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Embeddable
    public static class UserCompetitionConvoquerId implements Serializable {
        private Long userId;
        private Long competitionId;
    }


}
