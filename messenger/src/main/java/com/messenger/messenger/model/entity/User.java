package com.messenger.messenger.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Getter
@Setter
@Entity
public class User {
    @Id
    private UUID uuid;

    private String firstName;
    private String secondName;
    private String gender;
    private String password;
    private String role;
    private String country;
    private String city;
    private String dateOfBirth;

    @ManyToMany
    private Set<Dialogue> dialogues = new HashSet<>();

    @ManyToMany
    private Set<User> friends = new HashSet<>();
}
