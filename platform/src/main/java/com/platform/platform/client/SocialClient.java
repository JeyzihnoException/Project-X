package com.platform.platform.client;

import com.platform.platform.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocialClient implements AbstractClient {

//    @Value("${services.social}")
    private String host = "http://localhost:8080";

    private final RestTemplate restTemplate;

    public UserDTO getUserDetails(String userUuid) {
        return restTemplate.exchange(urlBuilder(host, "/" + userUuid),
                HttpMethod.GET,
                null,
                UserDTO.class).getBody();
    }

}
