package com.platform.platform.client;

import com.platform.platform.model.dto.RegistrationDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthClient implements AbstractClient {

    private final String host = "http://localhost:8082";
    private final RestTemplate restTemplate;

    public String userRegistration(RegistrationDataDTO registrationDataDTO) {
        return restTemplate.exchange(urlBuilder(host, "/registration"),
                HttpMethod.POST,
                new HttpEntity<>(registrationDataDTO),
                String.class)
                .getBody();
    }


}
