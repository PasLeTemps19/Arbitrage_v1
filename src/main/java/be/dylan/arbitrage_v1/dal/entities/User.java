package be.dylan.arbitrage_v1.dal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SoftDelete
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "user_")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150,nullable = true)
    private String name;

    @Column(length = 150,nullable = true)
    private String surname;

    @Column(length = 150,nullable = true)
    private String club;

    @Column(length = 150,nullable = false,unique = true)
    private String email;


    @Column(nullable = false)
    private boolean active;

    @CreatedDate
    @Column(nullable = false)
    private LocalDate createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate updateDate;

    @Column(length = 150,unique = true)
    private String token;



    @ManyToMany
    @JoinTable(
            name = "userCompetitionParticiper",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private Set<Competition> userCompetitionParticiper = new HashSet<>();



}
