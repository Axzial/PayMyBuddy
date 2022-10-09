package fr.axzial.paymybuddy.exception.types.auth.register;

import fr.axzial.paymybuddy.exception.api.CustomException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyRegisteredException extends CustomException {
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
