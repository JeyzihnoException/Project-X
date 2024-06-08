package com.social.social.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Community {
    @Id
    private UUID uuid;
    private String name;

    @OneToOne
    private User author;

    @OneToMany
    private Set<CommunityPost> posts = new HashSet<>();

    @ManyToMany(mappedBy = "community")
    private Set<User> members = new HashSet<>();

    public Community(String name, User user) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.author = user;
    }
}
