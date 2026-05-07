package be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserCompetitionConvoquerCreateFormDto {
    @NotNull(message = "L'utilisateur est obligatoire.")
    private Long userId;

    @NotNull(message = "La compétition est obligatoire.")
    private Long competitionId;

    private LocalDate convocDate;

}
