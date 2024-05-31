package com.social.social.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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
    private LocalDateTime dateOfBorn;

    @ManyToMany
    private Set<Dialogue> dialogues = new HashSet<>();

    @ManyToMany
    private Set<Community> communities = new HashSet<>();
}
