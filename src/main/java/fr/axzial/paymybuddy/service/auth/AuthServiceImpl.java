package fr.axzial.paymybuddy.service.auth;

import fr.axzial.paymybuddy.auth.utils.JwtUtils;
import fr.axzial.paymybuddy.dto.auth.AuthTokenDTO;
import fr.axzial.paymybuddy.dto.auth.login.AuthLoginDTO;
import fr.axzial.paymybuddy.dto.auth.register.AuthRegisterDTO;
import fr.axzial.paymybuddy.exception.types.auth.AccountNotFoundException;
import fr.axzial.paymybuddy.exception.types.auth.LoginFailureException;
import fr.axzial.paymybuddy.exception.types.auth.register.EmailAlreadyRegisteredException;
import fr.axzial.paymybuddy.exception.types.auth.register.UsernameAlreadyRegisteredException;
import fr.axzial.paymybuddy.exception.types.global.InternalServerErrorException;
import fr.axzial.paymybuddy.model.user.WebUser;
import fr.axzial.paymybuddy.service.user.WebUserService;
import fr.axzial.paymybuddy.utils.SpringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final WebUserService webUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AuthTokenDTO login(AuthLoginDTO authLoginDTO) {
        WebUser webUser = webUserService.findByEmail(authLoginDTO.getEmail()).orElseThrow(AccountNotFoundException::new);
        if (!passwordEncoder.matches(authLoginDTO.getPassword(), webUser.getPassword())) {
            throw new LoginFailureException();
        }
        String generatedToken = jwtUtils.generateJwtToken(webUser.getUuid());
        return new AuthTokenDTO(generatedToken);
    }

    @Override
    public AuthTokenDTO register(AuthRegisterDTO authRegisterDTO) {
        webUserService.findByUsernameOrEmail(authRegisterDTO.getUsername(), authRegisterDTO.getEmail())
                .stream()
                .findAny()
                .ifPresent(s -> {
                    if (s.getEmail().equalsIgnoreCase(authRegisterDTO.getEmail()))
                        throw new EmailAlreadyRegisteredException();
                    if (s.getUsername().equalsIgnoreCase(authRegisterDTO.getUsername()))
                        throw new UsernameAlreadyRegisteredException();
                    else throw new InternalServerErrorException();
                });
        WebUser created = webUserService.createDefaultWebUser(authRegisterDTO.getEmail(), authRegisterDTO.getUsername(), authRegisterDTO.getPassword());
        String generatedToken = jwtUtils.generateJwtToken(created.getUuid());
        return new AuthTokenDTO(generatedToken);
    }

}
