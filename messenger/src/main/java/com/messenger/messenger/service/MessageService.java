package com.messenger.messenger.service;

import com.messenger.messenger.manager.DialogueManager;
import com.messenger.messenger.manager.MessageManager;
import com.messenger.messenger.manager.UserManager;
import com.messenger.messenger.model.dto.MessageDTO;
import com.messenger.messenger.model.entity.Dialogue;
import com.messenger.messenger.model.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageManager messageManager;
    private final UserManager userManager;
    private final DialogueManager dialogueManager;

    public void saveMessage(MessageDTO messageDTO, UUID dialogueUuid) {
        Message message = new Message(messageDTO);
        Dialogue dialogue = dialogueManager.getByUuid(dialogueUuid).orElseThrow();
        dialogue.getMessages().add(message);

        dialogueManager.save(dialogue);
    }
}
