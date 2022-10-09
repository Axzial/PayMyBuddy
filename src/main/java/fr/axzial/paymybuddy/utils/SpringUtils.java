package fr.axzial.paymybuddy.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class SpringUtils {

    public String likeContains(String query){
        return "%" + query + "%";
    }


}
