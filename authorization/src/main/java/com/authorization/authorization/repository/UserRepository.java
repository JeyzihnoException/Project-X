package com.authorization.authorization.repository;

import com.authorization.authorization.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsUserByFirstNameAndPassword(String firstName, String secondName);

    Optional<User> findByFirstNameAndPassword(String firstName, String secondName);

    Optional<User> findUserByFirstName(String name);

}
