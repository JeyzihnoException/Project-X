package com.platform.platform.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDataDTO {
    private String firstName;
    private String secondName;
    private String salt;
    private String password;
    // Дата рождения
    private String dateOfBorn;
    private String country;
    private String city;
    private String gender;
}
