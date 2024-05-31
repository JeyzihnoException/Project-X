package com.social.social.model.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String secondName;
    private Set<FriendDTO> friends = new HashSet<>();
    private SelfDataDTO selfData;
    private Boolean self;
}
