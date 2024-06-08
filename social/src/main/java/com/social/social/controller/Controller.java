package com.social.social.controller;


import com.social.social.model.dto.CommunityDTO;
import com.social.social.model.dto.DialogueDTO;
import com.social.social.model.dto.UserDTO;
import com.social.social.service.CommunityService;
import com.social.social.service.DialogueService;
import com.social.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;
    private final DialogueService dialogueService;
    private final CommunityService communityService;

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

    @GetMapping("/users/get-all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/community/create")
    public ResponseEntity<Void> communityCreate(@RequestBody CommunityDTO communityDTO) {
        communityService.create(communityDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/community/get-all/{userUuid}")
    public ResponseEntity<List<CommunityDTO>> communityGetAll(@PathVariable UUID userUuid) {
        return new ResponseEntity<>(communityService.getAll(userUuid),
                HttpStatus.OK);
    }
}
