package com.social.social.controller;


import com.social.social.model.dto.DialogueDTO;
import com.social.social.model.dto.UserDTO;
import com.social.social.service.DialogueService;
import com.social.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;
    private final DialogueService dialogueService;

    @GetMapping("/{userUuid}")
    public UserDTO getUserInfo(@PathVariable UUID userUuid) {
        return userService.getUserDetails(userUuid);
    }

    @GetMapping("/friend/add/{userUuid}/{selfUuid}")
    public void addToFriend(@PathVariable String userUuid, @PathVariable String selfUuid) {
        userService.addToFriend(userUuid, selfUuid);
    }

    @GetMapping("/friend/delete/{userUuid}/{selfUuid}")
    public void deleteFromFriend(@PathVariable String userUuid, @PathVariable String selfUuid) {
        userService.deleteFromFriend(userUuid, selfUuid);
    }

    @GetMapping("/dialogue/get-all/{userUuid}")
    public void getAllDialogue(@PathVariable String userUuid) {

    }

    @GetMapping("/dialogue/get/{userUuid}/{selfUuid}")
    public ResponseEntity<DialogueDTO> getDialogue(@PathVariable UUID userUuid, @PathVariable UUID selfUuid) {
        return new ResponseEntity<>(dialogueService.getDialogue(userUuid, selfUuid), HttpStatus.OK);
    }

}
