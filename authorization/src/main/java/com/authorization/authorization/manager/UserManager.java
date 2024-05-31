package com.authorization.authorization.manager;

import com.authorization.authorization.model.entity.User;
import com.authorization.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository repository;

    public boolean isUserExist(String firstName, String secondName) {
        return repository.existsUserByFirstNameAndSecondName(firstName, secondName);
    }

    public void save(User user) {
        repository.save(user);
    }
}
