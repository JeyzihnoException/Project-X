package com.social.social.controller;


import com.social.social.manager.UserManager;
import com.social.social.model.dto.UserDTO;
import com.social.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;

    @GetMapping("/{userUuid}")
    public UserDTO getUserInfo(@PathVariable UUID userUuid) {
        return userService.getUserDetails(userUuid);
    }
}
