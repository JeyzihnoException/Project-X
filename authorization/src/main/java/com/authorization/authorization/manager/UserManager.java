package com.authorization.authorization.manager;

import com.authorization.authorization.model.entity.User;
import com.authorization.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository repository;

    public boolean isUserExist(String firstName, String password) {
        return repository.existsUserByFirstNameAndPassword(firstName, password);
    }

    public void save(User user) {
        repository.save(user);
    }

    public Optional<User> getByUserNameAndPassword(String firstName, String password) {
        return repository.findByFirstNameAndPassword(firstName, password);
    }

    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }
}
