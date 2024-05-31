package com.platform.platform.controller;

import com.platform.platform.client.AuthClient;
import com.platform.platform.client.SocialClient;
import com.platform.platform.model.dto.RegistrationDataDTO;
import com.platform.platform.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

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
        model.addObject("uuid", "хуй");
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

}
