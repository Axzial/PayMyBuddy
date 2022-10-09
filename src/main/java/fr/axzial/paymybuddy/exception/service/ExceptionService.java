package fr.axzial.paymybuddy.exception.service;

import fr.axzial.paymybuddy.exception.api.CustomException;
import fr.axzial.paymybuddy.exception.api.CustomExceptionDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class ExceptionService {

    @SneakyThrows
    @ExceptionHandler({CustomException.class})
    public CustomExceptionDTO handle(HttpServletResponse resp, CustomException e) {
        e.printStackTrace();
        resp.setStatus(e.getHttpStatus().value());
        return toDto(e);
    }

    public CustomExceptionDTO toDto(CustomException customException) {
        return new CustomExceptionDTO(
                customException.getHttpStatus().getReasonPhrase(),
                customException.getHttpStatus().value(),
                customException.getTimestamp(),
                customException.getMeta()
        );
    }

}
