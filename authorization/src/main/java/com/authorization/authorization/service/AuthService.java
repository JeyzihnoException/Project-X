package com.authorization.authorization.service;

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
                registrationDataDTO.getCountry(),
                registrationDataDTO.getCity(),
                registrationDataDTO.getDateOfBorn());
        user.setRole(roleManager.getRoleByName("USER").orElseThrow());
        userManager.save(user);
    }

    public UserDetailsDTO authorization(AuthDTO authDTO) {
        User user = userManager.getByUserNameAndPassword(authDTO.getUserName(), authDTO.getPassword())
                .orElseThrow();
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
