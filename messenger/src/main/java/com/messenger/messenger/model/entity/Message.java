package com.messenger.messenger.model.entity;

import com.messenger.messenger.model.dto.MessageDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    private UUID uuid;

    private String content;
    private UUID authorId;
    private LocalDateTime sendTime;

    public Message(MessageDTO messageDTO) {
        this.uuid = UUID.randomUUID();
        this.content = messageDTO.getContent();
        this.authorId = messageDTO.getAuthorId();
        this.sendTime = messageDTO.getSendTime();
    }
}
