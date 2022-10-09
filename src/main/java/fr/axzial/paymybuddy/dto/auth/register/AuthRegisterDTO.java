package fr.axzial.paymybuddy.dto.auth.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String username;
}
