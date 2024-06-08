package com.authorization.authorization.service;

import com.authorization.authorization.exception.BadCredentialsException;
import com.authorization.authorization.manager.RoleManager;
import com.authorization.authorization.manager.UserManager;
import com.authorization.authorization.model.dto.AuthDTO;
import com.authorization.authorization.model.dto.RegistrationDataDTO;
import com.authorization.authorization.model.dto.UserDetailsDTO;
import com.authorization.authorization.model.entity.Privilege;
import com.authorization.authorization.model.entity.User;
import com.authorization.authorization.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserManager userManager;
    private final RoleManager roleManager;

    public void registration(RegistrationDataDTO registrationDataDTO) {
        UserUtil.validationUserFields(registrationDataDTO);
        User user = new User(registrationDataDTO.getFirstName(),
                registrationDataDTO.getSecondName(),
                registrationDataDTO.getGender(),
                registrationDataDTO.getPassword(),
                registrationDataDTO.getSalt(),
                registrationDataDTO.getCountry(),
                registrationDataDTO.getCity(),
                registrationDataDTO.getDateOfBorn());
        user.setRole(roleManager.getRoleByName("USER").orElseThrow());
        userManager.save(user);
    }

    public UserDetailsDTO authorization(AuthDTO authDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userManager.getByUserName(authDTO.getUserName())
                .orElseThrow();
        if (!user.getPassword().equals(UserUtil.hashPassword(authDTO.getPassword(), user.getSalt()))) {
            throw new BadCredentialsException("Не правильный логин или пароль!");
        }
        return UserDetailsDTO
                .builder()
                .role(user.getRole().getName())
                .uuid(user.getUuid())
                .privileges(user.getRole()
                        .getPrivileges()
                        .stream()
                        .map(Privilege::getName)
                        .toList())
                .build();
    }

}
