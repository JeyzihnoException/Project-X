package com.messenger.messenger.service;

import com.messenger.messenger.manager.DialogueManager;
import com.messenger.messenger.manager.UserManager;
import com.messenger.messenger.model.dto.DialogueDTO;
import com.messenger.messenger.model.dto.MessageDTO;
import com.messenger.messenger.model.dto.UserDTO;
import com.messenger.messenger.model.entity.Dialogue;
import com.messenger.messenger.model.entity.Message;
import com.messenger.messenger.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DialogueService {

    private final DialogueManager dialogueManager;
    private final UserManager userManager;

    public DialogueDTO getDialogue(UUID userUuid, UUID selfUuid) {
        Dialogue dialogue = dialogueManager
                .getByUsersUuids(userManager.getUserByUuid(userUuid).orElseThrow(),
                        userManager.getUserByUuid(selfUuid).orElseThrow())
                .orElseGet(() -> create(userUuid, selfUuid));

        User partner = dialogue
                .getOwners()
                .stream()
                .filter(user -> user.getUuid()
                        .equals(userUuid))
                .findFirst()
                .orElseThrow();
        return DialogueDTO
                .builder()
                .uuid(dialogue.getUuid())
                .messages(dialogue.getMessages()
                        .stream()
                        .map(message -> {
                            User author = userManager.getUserByUuid(message.getAuthorId()).orElseThrow();
                            return MessageDTO
                                    .builder()
                                    .content(message.getContent())
                                    .sendTime(message.getSendTime())
                                    .authorFirsName(author.getFirstName())
                                    .authorSecondName(author.getSecondName())
                                    .build();
                        })
                        .toList())
                .owners(dialogue.getOwners().stream()
                        .map(user -> UserDTO
                                .builder()
                                .firstName(user.getFirstName())
                                .secondName(user.getSecondName())
                                .build())
                        .toList())
                .partner(partner.getFirstName() + " " + partner.getSecondName())
                .partnerId(userUuid.toString())
                .build();
    }


    public List<DialogueDTO> getAll(UUID userUuid) {
        return dialogueManager
                .getAll(userUuid)
                .stream()
                .map(dialogue -> DialogueDTO
                        .builder()
                        .uuid(dialogue.getUuid())
                        .messages(dialogue.getMessages()
                                .stream()
                                .map(message -> {
                                    User author = userManager.getUserByUuid(message.getAuthorId()).orElseThrow();
                                    return MessageDTO
                                            .builder()
                                            .content(message.getContent())
                                            .sendTime(message.getSendTime())
                                            .authorFirsName(author.getFirstName())
                                            .authorSecondName(author.getSecondName())
                                            .build();
                                })
                                .toList())
                        .owners(dialogue.getOwners().stream()
                                .map(user -> UserDTO
                                        .builder()
                                        .firstName(user.getFirstName())
                                        .secondName(user.getSecondName())
                                        .build())
                                .toList())
                        .partner(dialogue
                                .getOwners()
                                .stream()
                                .filter(user -> !user.getUuid().equals(userUuid))
                                .findFirst()
                                .map(user -> user.getFirstName() + " " + user.getSecondName())
                                .orElseThrow())
                        .partnerId(dialogue
                                .getOwners()
                                .stream()
                                .filter(user -> !user.getUuid().equals(userUuid))
                                .findFirst()
                                .orElseThrow()
                                .getUuid().toString())
                        .lastMessage(dialogue
                                .getMessages()
                                .stream().min(Comparator.comparing(Message::getSendTime))
                                .orElseThrow()
                                .getContent())
                        .lastMessageAuthor(dialogue
                                .getMessages()
                                .stream().min(Comparator.comparing(Message::getSendTime))
                                .orElseThrow().getAuthorId().equals(userUuid)
                                ?
                                "Вы" :
                                userManager.getUserByUuid(dialogue
                                        .getMessages()
                                        .stream().min(Comparator.comparing(Message::getSendTime))
                                        .orElseThrow().getAuthorId()).orElseThrow().getFirstName())
                        .build())
                .toList();

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
