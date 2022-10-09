package fr.axzial.paymybuddy.controller.auth;

import fr.axzial.paymybuddy.dto.auth.AuthTokenDTO;
import fr.axzial.paymybuddy.dto.auth.login.AuthLoginDTO;
import fr.axzial.paymybuddy.dto.auth.register.AuthRegisterDTO;
import fr.axzial.paymybuddy.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/login")
    public AuthTokenDTO login(@RequestBody AuthLoginDTO authLoginDTO) {
        return authService.login(authLoginDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/register")
    public AuthTokenDTO register(@RequestBody AuthRegisterDTO authRegisterDTO) {
        return authService.register(authRegisterDTO);
    }

}
