package be.dylan.arbitrage_v1.pl.dtos.ScheduledEmail;

import be.dylan.arbitrage_v1.dal.enums.ConvocationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduledEmailCreateFormDto {
    @NotNull(message = "La compétition est obligatoire.")
    private Long competitionId;

    @NotNull(message = "La date d'envoi est obligatoire.")
    private LocalDateTime sendAt;

    @NotNull(message = "L'objet est obligatoire.")
    private String subject;

    private String message;
    private String introMessage;

    @NotNull(message = "Le statut cible est obligatoire.")
    private ConvocationStatus targetStatus;
}