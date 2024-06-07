package com.platform.platform.client;

import com.platform.platform.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    public List<UserDTO> getAllUsers() {
        return restTemplate.exchange(urlBuilder(host, "/users/get-all"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>() {
                }).getBody();
    }

    public void addToFriend(String userUuid, String selfUuid) {
        restTemplate.exchange(urlBuilder(host, "/friend/add/" + userUuid + "/" + selfUuid),
                HttpMethod.GET,
                null,
                Void.class);
    }

    public void deleteFromFriend(String userUuid, String selfUuid) {
        restTemplate.exchange(urlBuilder(host, "/friend/delete/" + userUuid + "/" + selfUuid),
                HttpMethod.GET,
                null,
                Void.class);
    }

}
