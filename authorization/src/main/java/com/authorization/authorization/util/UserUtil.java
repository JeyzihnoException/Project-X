package com.authorization.authorization.util;

import com.authorization.authorization.exception.BadUserFieldException;
import com.authorization.authorization.model.dto.RegistrationDataDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public void validationUserFields(RegistrationDataDTO registrationDataDTO) {
        if (registrationDataDTO.getFirstName().length() > 15) {
            throw new BadUserFieldException("Firstname is too long!");
        } else if (registrationDataDTO.getSecondName().length() > 15) {
            throw new BadUserFieldException("Secondname is too long!");
        }
    }
}
