package com.social.social.service;

import com.social.social.manager.DialogueManager;
import com.social.social.manager.UserManager;
import com.social.social.model.dto.DialogueDTO;
import com.social.social.model.dto.MessageDTO;
import com.social.social.model.dto.UserDTO;
import com.social.social.model.entity.Dialogue;
import com.social.social.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DialogueService {
    private final DialogueManager dialogueManager;
    private final UserManager userManager;

    public DialogueDTO getDialogue(UUID userUuid, UUID selfUuid) {
        Dialogue dialogue = dialogueManager
                .getByUsersUuids(userUuid, selfUuid)
                .orElse(create(userUuid, selfUuid));
        return DialogueDTO
                .builder()
                .uuid(dialogue.getUuid())
                .messages(dialogue.getMessages()
                        .stream()
                        .map(message -> MessageDTO
                                .builder()
                                .content(message.getContent())
                                .sendTime(message.getSendTime())
                                .authorId(message.getAuthorId())
                                .build())
                        .collect(Collectors.toSet()))
                .owners(dialogue.getOwners().stream()
                        .map(user -> UserDTO
                                .builder()
                                .firstName(user.getFirstName())
                                .secondName(user.getSecondName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    private Dialogue create(UUID userUuid, UUID selfUuid) {
        User user = userManager.getUserByUuid(userUuid).orElseThrow();
        User self = userManager.getUserByUuid(selfUuid).orElseThrow();
        Dialogue dialogue = new Dialogue();
        dialogue.getOwners().add(user);
        dialogue.getOwners().add(self);
        dialogueManager.save(dialogue);
        return dialogue;
    }

}
