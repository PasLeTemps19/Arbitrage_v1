package be.dylan.arbitrage_v1.pl.dtos.competition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CompetitionUpdateFormDto {

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(min = 2, max = 150, message = "Le nom doit contenir entre 2 et 150 caractères.")
    private String name;

    @NotNull(message = "La date est obligatoire.")
    private LocalDate date;

    @NotNull(message = "L'heure est obligatoire.")
    private LocalTime time;

    @Size(max = 1500, message = "La description doit contenir maximum 1500 caractères.")
    private String description;

    @NotNull(message = "La saison est obligatoire.")
    private Long seasonId;

    @NotNull(message = "Le gymnasium est obligatoire.")
    private Long gymnasiumId;
}
