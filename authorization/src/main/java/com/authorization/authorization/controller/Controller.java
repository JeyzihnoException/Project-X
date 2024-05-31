package com.authorization.authorization.controller;

import com.authorization.authorization.model.dto.RegistrationDataDTO;
import com.authorization.authorization.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class Controller {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDataDTO registrationDataDTO) {
        authService.registration(registrationDataDTO);
        return new ResponseEntity<>("Registration was successful!", HttpStatus.OK);
    }

}
