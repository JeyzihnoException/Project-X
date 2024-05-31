package com.social.social.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    @ManyToMany
    private Set<Dialogue> dialogues = new HashSet<>();

    @ManyToMany
    private Set<Community> communities = new HashSet<>();
}
