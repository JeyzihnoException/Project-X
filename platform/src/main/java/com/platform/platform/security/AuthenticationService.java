package com.platform.platform.security;

import com.platform.platform.client.AuthClient;
import com.platform.platform.model.dto.AuthDTO;
import com.platform.platform.model.dto.UserDetailsDTO;
import com.platform.platform.util.Util;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Configuration
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationProvider {
    private final AuthClient authClient;
    private final Logger logger = LoggerFactory.getLogger("authentication-controller");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            String username = authentication.getName();
            String password = authentication.getCredentials().toString();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            UserDetailsDTO userDetails = authClient.userAuthorization(new AuthDTO(username, password));

            Set<SimpleGrantedAuthority> authorities = userDetails
                    .getPrivileges()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userDetails.getRole()));
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, password, authorities);
            authToken.setDetails(userDetails.getUuid()); // Сохраняем UUID в Authentication

            // Добавляем UUID в куки
            addUuidToCookie(userDetails.getUuid(), response);

            return authToken;
        } catch (Exception e) {
            logger.warn("[" + LocalDate.now() + "]" + " [WARNING] from " + Util.getRequestRemoteAddr()
                    + " with username = " + authentication.getName() + " and password ="
                    + authentication.getCredentials() + " /login > [AUTH] [LOGIN] [UNKNOWN USER]");
            return null;
        }

    }

    private void addUuidToCookie(UUID uuid, HttpServletResponse response) {
        Cookie cookie = new Cookie("userUuid", uuid.toString());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
