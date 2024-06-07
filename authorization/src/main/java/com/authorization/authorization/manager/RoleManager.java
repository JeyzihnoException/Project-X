package com.authorization.authorization.manager;

import com.authorization.authorization.model.entity.Role;
import com.authorization.authorization.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleManager {
    private final RoleRepository repository;

    public Optional<Role> getRoleByName(String name) {
        return repository.findRoleByName(name);
    }
}
