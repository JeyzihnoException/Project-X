package com.authorization.authorization.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Privilege {
    @Id
    private UUID uuid;
    private String name;
}
