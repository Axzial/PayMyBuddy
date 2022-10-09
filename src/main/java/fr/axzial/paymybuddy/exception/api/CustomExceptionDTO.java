package fr.axzial.paymybuddy.exception.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomExceptionDTO {

    String status;
    Integer code;
    Timestamp timestamp;
    Map<String, Object> meta;

}
