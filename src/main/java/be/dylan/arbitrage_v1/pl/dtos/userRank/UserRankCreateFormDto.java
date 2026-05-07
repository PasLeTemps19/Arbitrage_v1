package be.dylan.arbitrage_v1.pl.dtos.userRank;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRankCreateFormDto {

    @NotNull(message = "L'utilisateur est obligatoire.")
    private Long userId;

    @NotNull(message = "Le rang est obligatoire.")
    private Long rankId;

    private LocalDate obtentionDate;


}