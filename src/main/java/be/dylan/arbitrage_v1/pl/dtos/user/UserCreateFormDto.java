package be.dylan.arbitrage_v1.pl.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateFormDto {

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

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, max = 150, message = "Le mot de passe doit contenir au moins 8 caractères.")
    private String password;

    @NotBlank(message = "La confirmation du mot de passe est obligatoire.")
    private String confirmPassword;
}
