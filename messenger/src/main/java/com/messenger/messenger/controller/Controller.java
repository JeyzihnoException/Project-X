package com.messenger.messenger.controller;

import com.messenger.messenger.model.dto.DialogueDTO;
import com.messenger.messenger.model.dto.MessageDTO;
import com.messenger.messenger.service.DialogueService;
import com.messenger.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequiredArgsConstructor
public class Controller {

    private final DialogueService dialogueService;
    private final MessageService messageService;

    @GetMapping("/dialogue/get-all/{userUuid}")
    public ResponseEntity<List<DialogueDTO>> getAllDialogue(@PathVariable UUID userUuid) {
        return new ResponseEntity<>(dialogueService.getAll(userUuid), HttpStatus.OK);
    }

    @GetMapping("/dialogue/get/{userUuid}/{selfUuid}")
    public ResponseEntity<DialogueDTO> getDialogue(@PathVariable UUID userUuid, @PathVariable UUID selfUuid) {
        return new ResponseEntity<>(dialogueService.getDialogue(userUuid, selfUuid), HttpStatus.OK);
    }

    @PostMapping("/message/send/{dialogueUuid}")
    public void sendMessage(@RequestBody MessageDTO messageDTO, @PathVariable UUID dialogueUuid) {
        messageService.saveMessage(messageDTO, dialogueUuid);
    }

}
