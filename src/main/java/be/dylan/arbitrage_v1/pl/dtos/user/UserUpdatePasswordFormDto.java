package be.dylan.arbitrage_v1.pl.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordFormDto {
    @NotBlank(message = "L'ancien mot de passe est obligatoire.")
    @Size(min = 8, max = 150)
    private String oldPassword;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, max = 150, message = "Le mot de passe doit contenir au moins 8 caractères.")
    private String password;

    @NotBlank(message = "La confirmation du mot de passe est obligatoire.")
    private String confirmPassword;
}
