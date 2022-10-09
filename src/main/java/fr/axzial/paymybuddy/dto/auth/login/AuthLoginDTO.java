package fr.axzial.paymybuddy.dto.auth.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
