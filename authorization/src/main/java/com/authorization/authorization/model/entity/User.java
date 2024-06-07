package com.authorization.authorization.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    private UUID uuid;

    private String firstName;
    private String secondName;
    private String gender;
    private String password;
    private String country;
    private String city;
    private String dateOfBirth;

    @ManyToOne
    private Role role;

    public User(String firstName, String secondName, String gender, String password,
                String country,
                String city,
                String dateOfBorn) {
        this.uuid = UUID.randomUUID();
        this.gender = gender;
        this.password = password;
        this.country = country;
        this.city = city;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBorn;
    }
}
