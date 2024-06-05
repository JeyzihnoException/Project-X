package com.social.social.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DialogueDTO {
    private UUID uuid;
    private Set<MessageDTO> messages ;
    private Set<UserDTO> owners;
}