package be.dylan.arbitrage_v1.pl.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
}
