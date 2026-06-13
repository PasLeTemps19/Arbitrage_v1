package be.dylan.arbitrage_v1.pl.dtos.userCompetitionConvoquer;

import be.dylan.arbitrage_v1.dal.enums.ConvocationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompetitionConvoquerUpdateStatusDto {
    @NotNull(message = "Le statut est obligatoire.")
    private ConvocationStatus status;
}