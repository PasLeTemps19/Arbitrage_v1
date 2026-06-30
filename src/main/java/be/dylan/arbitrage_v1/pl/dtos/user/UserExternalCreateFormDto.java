package be.dylan.arbitrage_v1.pl.dtos.user;

import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserExternalCreateFormDto {

    @NotBlank(message = "Le nom est obligatoire.")
    private String name;

    @NotBlank(message = "Le prénom est obligatoire.")
    private String surname;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "L'email est invalide.")
    private String email;


    private String department;

    @NotNull(message = "Le rang Kata est obligatoire.")
    private RankStyle rankStyleKata;

    @NotNull(message = "Le type Kata est obligatoire.")
    private RankType rankTypeKata;

    @NotNull(message = "Le rang Kumite est obligatoire.")
    private RankStyle rankStyleKumite;

    @NotNull(message = "Le type Kumite est obligatoire.")
    private RankType rankTypeKumite;

    private LocalDate obtentionDateKata;
    private LocalDate obtentionDateKumite;


}