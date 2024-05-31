package com.authorization.authorization.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String secondName;
    private SelfDataDTO selfData;
}
