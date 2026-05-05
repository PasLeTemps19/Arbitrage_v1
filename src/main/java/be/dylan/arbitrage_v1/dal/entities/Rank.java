package be.dylan.arbitrage_v1.dal.entities;

import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")

public class Rank {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 150, nullable = false)
    private RankStyle style;

    @Enumerated(EnumType.STRING)
    @Column(length = 150, nullable = false)
    private RankType type;




}
