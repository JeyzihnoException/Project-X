package com.social.social.manager;

import com.social.social.model.entity.Dialogue;
import com.social.social.repository.DialogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DialogueManager {
    private final DialogueRepository repository;

    public Optional<Dialogue> getByUsersUuids(UUID userUuid, UUID selfUuid) {
        return repository.getDialogueByOwnersUuidIn(Set.of(userUuid, selfUuid));
    }

    public void save(Dialogue dialogue) {
        repository.save(dialogue);
    }
}
