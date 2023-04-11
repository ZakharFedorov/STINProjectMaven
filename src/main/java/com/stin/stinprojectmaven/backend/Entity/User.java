package com.stin.stinprojectmaven.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int code;
    private Boolean verified;

    public User() {
    }
    public User(String first_name, String last_name, String email, String password, int code, Boolean verified) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.verified = verified;
    }
}