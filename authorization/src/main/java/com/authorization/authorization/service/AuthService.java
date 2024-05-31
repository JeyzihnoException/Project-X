package com.authorization.authorization.service;

import com.authorization.authorization.manager.UserManager;
import com.authorization.authorization.model.dto.RegistrationDataDTO;
import com.authorization.authorization.model.entity.User;
import com.authorization.authorization.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserManager userManager;

    public void registration(RegistrationDataDTO registrationDataDTO) {
        UserUtil.validationUserFields(registrationDataDTO);
        User user = new User(registrationDataDTO.getFirstName(),
                registrationDataDTO.getSecondName(),
                registrationDataDTO.getGender(),
                LocalDateTime.now());
        userManager.save(user);
    }
}
