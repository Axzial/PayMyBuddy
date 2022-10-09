package fr.axzial.paymybuddy.exception.api;

import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

    private String title;
    private HttpStatus httpStatus;
    private Timestamp timestamp;
    private Map<String, Object> meta;

}
