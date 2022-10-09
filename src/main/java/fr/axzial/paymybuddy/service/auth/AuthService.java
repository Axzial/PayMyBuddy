package fr.axzial.paymybuddy.service.auth;

import fr.axzial.paymybuddy.dto.auth.AuthTokenDTO;
import fr.axzial.paymybuddy.dto.auth.login.AuthLoginDTO;
import fr.axzial.paymybuddy.dto.auth.register.AuthRegisterDTO;

public interface AuthService {
    AuthTokenDTO login(AuthLoginDTO authLoginDTO);

    AuthTokenDTO register(AuthRegisterDTO authRegisterDTO);
}
