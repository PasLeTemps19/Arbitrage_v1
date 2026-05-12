package be.dylan.arbitrage_v1.pl.dtos.user;

import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserCompleteProfileFormDto {

    @NotBlank(message = "Le club est obligatoire.")
    @Size(min = 2, max = 150, message = "Le club doit contenir entre 2 et 150 caractères.")
    private String club;

    @NotNull(message = "Le style du premier rang est obligatoire.")
    private RankStyle rankStyle1;

    @NotNull(message = "Le type du premier rang est obligatoire.")
    private RankType rankType1;

    @NotNull(message = "Le style du deuxième rang est obligatoire.")
    private RankStyle rankStyle2;

    @NotNull(message = "Le type du deuxième rang est obligatoire.")
    private RankType rankType2;

    private LocalDate obtentionDate1;

    private LocalDate obtentionDate2;
}