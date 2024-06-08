package com.platform.platform.controller;

import com.platform.platform.client.AuthClient;
import com.platform.platform.client.MessengerClient;
import com.platform.platform.client.SocialClient;
import com.platform.platform.model.dto.DialogueDTO;
import com.platform.platform.model.dto.MessageDTO;
import com.platform.platform.model.dto.RegistrationDataDTO;
import com.platform.platform.model.dto.UserDTO;
import com.platform.platform.model.event.MessageEvent;
import com.platform.platform.model.event.Processor;
import com.platform.platform.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController("/main")
@RequiredArgsConstructor
public class Controller {

    private final AuthClient authClient;
    private final SocialClient socialClient;
    private final MessengerClient messengerClient;
    private final Processor processor;


    @GetMapping(path = "/message/listen",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MessageEvent> messageListener() {
        return Flux.create(sink -> processor.register(sink::next));
    }


    @GetMapping("/")
    public ModelAndView getMainPage(@CookieValue("userUuid") String uuid) {
        ModelAndView model = new ModelAndView("main-page");
        UserDTO userDetails = socialClient.getUserDetails(uuid);

        userDetails.setSelf(true);
        model.addObject("userData", userDetails);
        model.addObject("uuid", uuid);
        model.addObject("privileges", Util.getCurrentUserPrivileges());
        return model;
    }

    @GetMapping("/{userUuid}")
    public ModelAndView getUserPage(@PathVariable String userUuid, @CookieValue("userUuid") String uuid) {
        ModelAndView model = new ModelAndView("main-page");
        UserDTO userDetails = socialClient.getUserDetails(userUuid);
        userDetails.setSelf(uuid.equals(userUuid));
        model.addObject("userData", userDetails);
        model.addObject("isFriend", userDetails.getFriends()
                .stream()
                .filter(friendDTO -> friendDTO.getUuid().equals(UUID.fromString(uuid)))
                .collect(Collectors.toSet()).isEmpty());
        model.addObject("uuid", userUuid);
        model.addObject("privileges", Util.getCurrentUserPrivileges());
        return model;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public ModelAndView registration(RegistrationDataDTO registrationDataDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        ModelAndView modelAndView = new ModelAndView();
        registrationDataDTO.setSalt(Util.generateSalt());
        registrationDataDTO.setPassword(Util.hashPassword(registrationDataDTO.getPassword(), registrationDataDTO.getSalt()));
        modelAndView.addObject("result", authClient.userRegistration(registrationDataDTO));
        return modelAndView;
    }

    @GetMapping("/authorization")
    public ModelAndView getAuthorizationPage() {
        return new ModelAndView("authorization");
    }


    @GetMapping("/friend/add/{userUuid}")
    public void addToFriend(@PathVariable String userUuid, @CookieValue("userUuid") String selfUuid) {
        socialClient.addToFriend(userUuid, selfUuid);
    }

    @GetMapping("/friend/delete/{userUuid}")
    public void deleteFromFriend(@PathVariable String userUuid, @CookieValue("userUuid") String selfUuid) {
        socialClient.deleteFromFriend(userUuid, selfUuid);
    }

    @GetMapping("/friend/get-all")
    public ModelAndView getFriends(@CookieValue("userUuid") String selfUuid) {
        ModelAndView modelAndView = new ModelAndView("friends-page");
        modelAndView.addObject("friends", socialClient.getUserDetails(selfUuid).getFriends());
        return modelAndView;
    }

    @GetMapping("/dialogue/get/{userUuid}")
    public ModelAndView getDialogue(@PathVariable String userUuid, @CookieValue("userUuid") String selfUuid) {
        ModelAndView modelAndView = new ModelAndView("dialogue");
        DialogueDTO dialogue = messengerClient.getDialogue(userUuid, selfUuid);
        dialogue.setMessages(dialogue
                .getMessages().stream()
                .sorted(Comparator.comparing(MessageDTO::getSendTime))
                .toList());
        modelAndView.addObject("dialogue", dialogue);
        modelAndView.addObject("partnerName", dialogue.getPartner());
        modelAndView.addObject("partnerId", userUuid);
        return modelAndView;
    }

    @GetMapping("/message/send/{dialogueUuid}/{message}")
    public void sendMessage(@PathVariable String dialogueUuid,
                            @PathVariable String message,
                            @CookieValue("userUuid") String selfUuid) {
        messengerClient.sendMessage(MessageDTO
                .builder()
                .content(message)
                .sendTime(LocalDateTime.now())
                .authorId(UUID.fromString(selfUuid))
                .build(), dialogueUuid);
        processor.process(MessageEvent
                .builder()
                .dialogueId(UUID.fromString(dialogueUuid))
                .message(message)
                .sendTime("12:00")
                .build());
    }

    @GetMapping("/dialogue/get-all")
    public ModelAndView getAllDialogues(
            @CookieValue("userUuid") String selfUuid) {
        ModelAndView modelAndView = new ModelAndView("dialogues");
        modelAndView.addObject("dialogues", messengerClient.getAllDialogues(selfUuid));
        return modelAndView;
    }

    @GetMapping("/user/delete/{userUuid}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public void deleteUser(@PathVariable UUID userUuid) {
        authClient.deleteUser(userUuid);
    }

    @GetMapping("/admin/panel")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getAdminPanel() {
        ModelAndView modelAndView = new ModelAndView("admin-panel");
        modelAndView.addObject("users", socialClient.getAllUsers());
        return modelAndView;
    }
}
