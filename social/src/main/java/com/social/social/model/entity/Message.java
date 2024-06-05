package com.social.social.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Message {
    @Id
    private UUID uuid;

    private String content;
    private UUID authorId;
    private LocalDateTime sendTime;
}
