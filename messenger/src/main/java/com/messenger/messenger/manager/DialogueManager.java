package com.messenger.messenger.manager;


import com.messenger.messenger.model.entity.Dialogue;
import com.messenger.messenger.model.entity.User;
import com.messenger.messenger.repository.DialogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DialogueManager {
    private final DialogueRepository repository;

    public Optional<Dialogue> getByUsersUuids(User partner, User self) {
        return repository.findDialogueByOwners(partner.getUuid(), self.getUuid());
    }

    public void save(Dialogue dialogue) {
        repository.save(dialogue);
    }

    public Optional<Dialogue> getByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    public List<Dialogue> getAll(UUID uuid) {
        return repository.findDialoguesByOwnersUuidIn(List.of(uuid));
    }
}
