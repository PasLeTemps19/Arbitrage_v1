package be.dylan.arbitrage_v1.pl.dtos.user;

import be.dylan.arbitrage_v1.dal.enums.RankStyle;
import be.dylan.arbitrage_v1.dal.enums.RankType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserCompleteProfileFormDto {

    @NotBlank(message = "Le club est obligatoire.")
    @Size(min = 2, max = 150, message = "Le club doit contenir entre 2 et 150 caractères.")
    private String club;

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

    @Pattern(regexp = "^(0|\\+33)[1-9](\\d{2}){4}$", message = "Numéro de téléphone invalide.")
    private String phoneNumberTwo;

    @Pattern(regexp = "^FR\\d{2}[A-Z0-9]{23}$", message = "IBAN français invalide.")
    private String iban;

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