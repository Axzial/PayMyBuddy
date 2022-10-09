package fr.axzial.paymybuddy.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@UtilityClass
public class HttpUtils {

    public Optional<String> extractJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return Optional.of(headerAuth.substring(7));
        }
        return Optional.empty();
    }

}
