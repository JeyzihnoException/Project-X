package com.authorization.authorization.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private String role;
    private String country;
    private String city;
    private String dateOfBirth;

    public User(String firstName, String secondName, String gender, String password,
                String country,
                String city,
                String dateOfBorn) {
        this.uuid = UUID.randomUUID();
        this.gender = gender;
        this.password = password;
        this.role = "USER";
        this.country = country;
        this.city = city;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBorn;
    }
}
