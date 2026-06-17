package be.dylan.arbitrage_v1.dal.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

@SoftDelete
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Gymnasium {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150,nullable = false)
    private String name;

    @Column(length = 150,nullable = false)
    private String street;

    @Column(nullable = true)
    private Integer number;

    @Column(nullable = false)
    private int postCode;

    @Column(length = 150,nullable = false)
    private String city;

    @Column(length = 150)
    private String responsable;

    @Column(length = 1500)
    private String description;
}
