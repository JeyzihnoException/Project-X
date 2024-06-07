package com.authorization.authorization.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    private UUID uuid;
    private String name;
    @ManyToMany
    private Set<Privilege> privileges = new HashSet<>();

    public Role(String role) {
        this.uuid = UUID.randomUUID();
        this.name = role;
    }
}
