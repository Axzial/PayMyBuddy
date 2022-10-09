package fr.axzial.paymybuddy.exception.types.auth;

import fr.axzial.paymybuddy.exception.api.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.Map;

public class LoginFailureException extends CustomException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
