package com.platform.platform.model.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private UUID uuid;
    private String firstName;
    private String secondName;
    private Set<FriendDTO> friends;
    private SelfDataDTO selfData;
    private Boolean self;
}
