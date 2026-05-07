package be.dylan.arbitrage_v1.dal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SoftDelete
@Entity
public class UserCompetitionConvoquer implements Serializable {

    @EmbeddedId
    private UserCompetitionConvoquerId id;

    @Column(nullable = false)
    private LocalDate convocDate;

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
