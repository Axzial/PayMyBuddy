package fr.axzial.paymybuddy.exception.types.transaction;

import fr.axzial.paymybuddy.exception.api.CustomException;
import org.springframework.http.HttpStatus;

public class TransactionNotFoundException extends CustomException {
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
