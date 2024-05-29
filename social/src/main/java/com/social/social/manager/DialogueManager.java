package com.social.social.manager;

import com.social.social.repository.DialogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DialogueManager {
    private final DialogueRepository repository;
}
