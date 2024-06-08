package com.platform.platform.client;

import com.platform.platform.model.dto.AuthDTO;
import com.platform.platform.model.dto.RegistrationDataDTO;
import com.platform.platform.model.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthClient implements AbstractClient {

    private final String host = "http://auth-app:8082";
    private final RestTemplate restTemplate;

    public String userRegistration(RegistrationDataDTO registrationDataDTO) {
        return restTemplate.exchange(urlBuilder(host, "/registration"),
                        HttpMethod.POST,
                        new HttpEntity<>(registrationDataDTO),
                        String.class)
                .getBody();
    }

    public UserDetailsDTO userAuthorization(AuthDTO authDTO) {
        return restTemplate.exchange(urlBuilder(host, "/authorization"),
                        HttpMethod.POST,
                        new HttpEntity<>(authDTO),
                        UserDetailsDTO.class)
                .getBody();
    }

    public void deleteUser(UUID uuid) {
        restTemplate.exchange(urlBuilder(host, "/user/delete/" + uuid),
                HttpMethod.GET,
                null,
                Void.class);
    }
}
