package com.platform.platform.controller;

import com.platform.platform.client.AuthClient;
import com.platform.platform.model.dto.UserDTO;
import com.platform.platform.model.dto.RegistrationDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("/main")
@RequiredArgsConstructor
public class Controller {

    private final AuthClient authClient;

    @GetMapping("/")
    public ModelAndView getMainPage() {
        ModelAndView model = new ModelAndView("main-page");
        model.addObject("mainPageData", new UserDTO());
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
}
