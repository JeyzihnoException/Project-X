package com.authorization.authorization.controller;

import com.authorization.authorization.manager.UserManager;
import com.authorization.authorization.model.dto.AuthDTO;
import com.authorization.authorization.model.dto.RegistrationDataDTO;
import com.authorization.authorization.model.dto.UserDetailsDTO;
import com.authorization.authorization.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@RestController()
@RequiredArgsConstructor
public class Controller {

    private final AuthService authService;
    private final UserManager userManager;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDataDTO registrationDataDTO) {
        authService.registration(registrationDataDTO);
        return new ResponseEntity<>("Registration was successful!", HttpStatus.OK);
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserDetailsDTO> authorization(@RequestBody AuthDTO authDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new ResponseEntity<>(authService.authorization(authDTO), HttpStatus.OK);
    }

    @GetMapping("/user/delete/{userUuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userUuid) {
        userManager.delete(userUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
