package com.social.social.manager;

import com.social.social.repository.DialogueRepository;
import com.social.social.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageManager {
    private final MessageRepository repository;

}
