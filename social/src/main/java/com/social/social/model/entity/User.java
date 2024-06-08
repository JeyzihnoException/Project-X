package com.social.social.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String salt;
    private String password;
    private String country;
    private String city;
    private String dateOfBirth;

    @ManyToOne
    private Role role;

    @ManyToMany
    private Set<Dialogue> dialogues = new HashSet<>();

    @ManyToMany
    private Set<Community> communities = new HashSet<>();

    @ManyToMany
    private Set<User> friends = new HashSet<>();

    @ManyToMany
    private Set<Community> community = new HashSet<>();
}
