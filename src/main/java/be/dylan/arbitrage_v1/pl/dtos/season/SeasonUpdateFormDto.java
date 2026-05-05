package be.dylan.arbitrage_v1.pl.dtos.season;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SeasonUpdateFormDto {

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(min = 2, max = 150, message = "Le nom doit contenir entre 2 et 150 caractères.")
    private String name;

    @NotNull(message = "La date de début est obligatoire.")
    private LocalDate startDate;

    @NotNull(message = "La date de fin est obligatoire.")
    private LocalDate endDate;
}