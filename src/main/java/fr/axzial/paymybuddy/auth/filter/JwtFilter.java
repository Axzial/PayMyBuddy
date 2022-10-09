package fr.axzial.paymybuddy.auth.filter;

import fr.axzial.paymybuddy.auth.utils.JwtUtils;
import fr.axzial.paymybuddy.utils.HttpUtils;
import io.netty.handler.codec.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> optionalJwt = HttpUtils.extractJwt(request);
        if (optionalJwt.isPresent() && jwtUtils.isValidToken(optionalJwt.get())) {
            String jwt = optionalJwt.get();
            // Must Be UUID (WebUser @Id)
            String uuidSubject = jwtUtils.getSubject(jwt);
            logger.info("uuidSubject : " + uuidSubject);
            // LOAD USER WITH UUID
            UserDetails userDetails = userDetailsService.loadUserByUsername(uuidSubject);
            // CREATE AUTH
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }



}
