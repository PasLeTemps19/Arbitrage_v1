package be.dylan.arbitrage_v1.dal.entities;

import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "userRankMap", joinColumns = @JoinColumn(name = "user_rank_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "style")
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Map<RankStyle, RankType> ranks = new HashMap<>();

    @Column(nullable = false)
    private LocalDate obtentionDate;
}