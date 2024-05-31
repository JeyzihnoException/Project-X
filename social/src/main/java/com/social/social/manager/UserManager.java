package com.social.social.manager;

import com.social.social.model.entity.User;
import com.social.social.repository.UserRepository;
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
}
