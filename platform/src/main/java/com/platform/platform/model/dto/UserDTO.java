package com.platform.platform.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String secondName;
    private Set<FriendDTO> friends;
    private SelfDataDTO selfData;
    private Boolean self;
}
