package com.messenger.messenger.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Dialogue {
    @Id
    private UUID uuid;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Message> messages = new HashSet<>();

    @ManyToMany
    private Set<User> owners = new HashSet<>();

    public Dialogue() {
        this.uuid = UUID.randomUUID();
    }
}
