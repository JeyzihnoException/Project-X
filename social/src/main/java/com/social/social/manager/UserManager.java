package com.social.social.manager;

import com.social.social.model.entity.User;
import com.social.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<User> getAll() {
        return repository.findAll();
    }
}
