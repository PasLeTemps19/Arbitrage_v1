package be.dylan.arbitrage_v1.dal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Competition {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150,nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "start_time",nullable = false)
    private LocalTime time;

    @Column(length = 1500)
    private String description;


    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne
    @JoinColumn(name = "gymnasium_id")
    private Gymnasium gymnasium;

}
