package com.messenger.messenger.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DialogueDTO {
    private UUID uuid;
    private List<MessageDTO> messages ;
    private List<UserDTO> owners;
    private String partner;
    private String partnerId;
}