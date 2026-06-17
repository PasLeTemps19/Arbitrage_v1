package be.dylan.arbitrage_v1.pl.dtos.gymnasium;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymnasiumUpdateFormDto {
    @NotBlank(message = "Le nom est obligatoire.")
    @Size(min = 2, max = 150, message = "Le nom doit contenir entre 2 et 150 caractères.")
    private String name;

    @NotBlank(message = "La rue est obligatoire.")
    @Size(min = 2, max = 150, message = "La rue doit contenir entre 2 et 150 caractères.")
    private String street;


    @Min(value = 1, message = "Le numéro doit être supérieur à 0.")
    private Integer number;

    @NotNull(message = "Le code postal est obligatoire.")
    @Min(value = 1000, message = "Le code postal est invalide.")
    @Max(value = 99999, message = "Le code postal est invalide.")
    private int postCode;

    @NotBlank(message = "La ville est obligatoire.")
    @Size(min = 2, max = 150, message = "La ville doit contenir entre 2 et 150 caractères.")
    private String city;

    @Size(max = 150, message = "Le responsable doit contenir maximum 150 caractères.")
    private String responsable;

    @Size(max = 1500, message = "La description doit contenir maximum 1500 caractères.")
    private String description;
}
