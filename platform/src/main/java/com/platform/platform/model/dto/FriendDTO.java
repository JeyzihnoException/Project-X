package com.platform.platform.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class FriendDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
}
