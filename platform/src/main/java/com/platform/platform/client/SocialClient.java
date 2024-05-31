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
    private String host;

    private final RestTemplate restTemplate;

    public UserDTO getMainPageData(String userUuid) {
        return restTemplate.exchange(urlBuilder(host, "/main/get-info",
                        Map.of("userUuid", userUuid)),
                HttpMethod.GET,
                null,
                UserDTO.class).getBody();
    }
}
