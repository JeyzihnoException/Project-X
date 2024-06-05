package com.messenger.messenger.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class MessageDTO {
    private String content;
    private UUID authorId;
    private String authorFirsName;
    private String authorSecondName;
    private LocalDateTime sendTime;
}
