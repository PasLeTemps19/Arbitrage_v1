package be.dylan.arbitrage_v1.pl.dtos.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateFormDto {

    @NotBlank(message = "Le nom est obligatoire.")
    @Size(min = 2, max = 150, message = "Le nom doit contenir entre 2 et 150 caractères.")
    private String name;

    @NotBlank(message = "Le prénom est obligatoire.")
    @Size(min = 2, max = 150, message = "Le prénom doit contenir entre 2 et 150 caractères.")
    private String surname;

    @NotBlank(message = "Le club est obligatoire.")
    @Size(min = 2, max = 150, message = "Le club doit contenir entre 2 et 150 caractères.")
    private String club;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "L'email est invalide.")
    @Size(max = 150, message = "L'email est trop long.")
    private String email;

    @NotBlank(message = "Le grade est obligatoire.")
    @Size(min = 2, max = 150, message = "Le grade doit contenir entre 2 et 150 caractères.")
    private String grade;

    @NotNull(message = "La date de naissance est obligatoire.")
    @Past(message = "La date de naissance doit être dans le passé.")
    private LocalDate birthDate;

    @NotBlank(message = "La rue est obligatoire.")
    @Size(min = 2, max = 150, message = "La rue doit contenir entre 2 et 150 caractères.")
    private String street;

    @Min(value = 1, message = "Le numéro doit être positif.")
    @Max(value = 9999, message = "Numéro de rue invalide.")
    private Integer number;

    @NotBlank(message = "Le code postal est obligatoire.")
    @Pattern(regexp = "^\\d{5}$", message = "Code postal invalide.")
    private String postalCode;

    @NotBlank(message = "La ville est obligatoire.")
    @Size(min = 2, max = 100, message = "La ville doit contenir entre 2 et 100 caractères.")
    private String city;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(regexp = "^(0|\\+33)[1-9](\\d{2}){4}$", message = "Numéro de téléphone invalide.")
    private String phoneNumber;

    private String phoneNumberTwo;

    @Pattern(regexp = "^FR\\d{2}[A-Z0-9]{23}$", message = "IBAN français invalide.")
    private String iban;
}
