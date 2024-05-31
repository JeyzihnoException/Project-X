package com.authorization.authorization.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDetailsDTO {
    private UUID uuid;
    private String role;
}
