package com.social.social.model.dto;

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
    private LocalDateTime sendTime;
}
