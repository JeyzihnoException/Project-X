package com.social.social.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class CommunityPost {
    @Id
    private UUID uuid;
    private String content;
    private String date;
}
