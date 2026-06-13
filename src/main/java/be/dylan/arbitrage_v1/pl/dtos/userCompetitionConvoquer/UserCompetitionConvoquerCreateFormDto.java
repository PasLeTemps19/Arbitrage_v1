package be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompetitionConvoquerCreateFormDto {
    @NotNull(message = "L'utilisateur est obligatoire.")
    private Long userId;

    @NotNull(message = "La compétition est obligatoire.")
    private Long competitionId;

    private String subject;
    private String message;
    private String introMessage;
}