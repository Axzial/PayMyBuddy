package fr.axzial.paymybuddy.exception.types.user.details;

import fr.axzial.paymybuddy.exception.api.CustomException;
import org.springframework.http.HttpStatus;

public class UserDetailsNotFoundException extends CustomException {
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
