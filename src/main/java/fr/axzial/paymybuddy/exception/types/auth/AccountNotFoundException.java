package fr.axzial.paymybuddy.exception.types.auth;

import fr.axzial.paymybuddy.exception.api.CustomException;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends CustomException {
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
