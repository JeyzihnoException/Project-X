package com.platform.platform.controller;

import com.platform.platform.client.AuthClient;
import com.platform.platform.client.SocialClient;
import com.platform.platform.model.dto.RegistrationDataDTO;
import com.platform.platform.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController("/main")
@RequiredArgsConstructor
public class Controller {

    private final AuthClient authClient;
    private final SocialClient socialClient;

    @GetMapping("/")
    public ModelAndView getMainPage(@CookieValue("userUuid") String uuid) {
        ModelAndView model = new ModelAndView("main-page");
        UserDTO userDetails = socialClient.getUserDetails(uuid);
        userDetails.setSelf(true);
        model.addObject("userData", userDetails);
        model.addObject("uuid", uuid);
        return model;
    }

    @GetMapping("/{userUuid}")
    public ModelAndView getUserPage(@PathVariable String userUuid, @CookieValue("userUuid") String uuid) {
        ModelAndView model = new ModelAndView("main-page");
        UserDTO userDetails = socialClient.getUserDetails(userUuid);
        if (uuid.equals(userUuid)) {
            userDetails.setSelf(true);
        } else {
            userDetails.setSelf(false);
        }
        model.addObject("userData", userDetails);
        model.addObject("isFriend", userDetails.getFriends()
                .stream()
                .filter(friendDTO -> friendDTO.getUuid().equals(UUID.fromString(uuid)))
                .collect(Collectors.toSet()).isEmpty());
        model.addObject("uuid", userUuid);
        return model;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage() {
        return new ModelAndView("registration");
    }

    @PostMapping("/registration")
    public ModelAndView registration(RegistrationDataDTO registrationDataDTO) {
        ModelAndView modelAndView = new ModelAndView();
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

//    @GetMapping("/friend/get-all")
//    public ModelAndView getFriends(@CookieValue("userUuid") String selfUuid) {
//        ModelAndView modelAndView = new ModelAndView();
//    }

}
