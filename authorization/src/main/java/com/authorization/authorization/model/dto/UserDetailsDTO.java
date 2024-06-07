package com.authorization.authorization.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDetailsDTO {
    private UUID uuid;
    private String role;
    private List<String> privileges;
}
