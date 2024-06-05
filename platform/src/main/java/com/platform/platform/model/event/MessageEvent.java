package com.platform.platform.model.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class MessageEvent {
    private UUID dialogueId;
    private String message;
    private String sendTime;
}
