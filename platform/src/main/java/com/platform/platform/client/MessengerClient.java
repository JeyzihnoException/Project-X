package com.platform.platform.client;

import com.platform.platform.model.dto.DialogueDTO;
import com.platform.platform.model.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessengerClient implements AbstractClient {

    private final RestTemplate restTemplate;
    private String host = "http://localhost:8083";

    public DialogueDTO getDialogue(String userUuid, String selfUuid) {
        return restTemplate.exchange(urlBuilder(host, "/dialogue/get/" + userUuid + "/" + selfUuid),
                        HttpMethod.GET,
                        null,
                        DialogueDTO.class)
                .getBody();
    }

    public List<DialogueDTO> getAllDialogues(String userUuid) {
        return restTemplate.exchange(urlBuilder(host, "/dialogue/get-all/" + userUuid),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DialogueDTO>>() {
                }).getBody();
    }

    public void sendMessage(MessageDTO messageDTO, String dialogueUuid) {
        restTemplate.exchange(urlBuilder(host, "/message/send/" + dialogueUuid),
                        HttpMethod.POST,
                        new HttpEntity<>(messageDTO),
                        Void.class)
                .getBody();
    }
}