package com.platform.platform.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDetailsDTO {
    private UUID uuid;
    private String role;
    private List<String> privileges = new ArrayList<>();
}
