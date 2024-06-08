package com.social.social.model.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityDTO {
    private String name;
    private UUID authorId;
}
