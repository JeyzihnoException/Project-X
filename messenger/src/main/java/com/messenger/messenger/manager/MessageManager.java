package com.messenger.messenger.manager;

import com.messenger.messenger.model.entity.Message;
import com.messenger.messenger.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageManager {
    private final MessageRepository repository;

    public void save(Message message) {
        repository.save(message);
    }

}
