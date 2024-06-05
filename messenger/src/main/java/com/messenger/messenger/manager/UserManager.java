package com.messenger.messenger.manager;

import com.messenger.messenger.model.entity.User;
import com.messenger.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository repository;

    public Optional<User> getUserByUuid(UUID uuid) {
        return repository.findById(uuid);
    }

    public void save(User user) {
        repository.save(user);
    }
}
