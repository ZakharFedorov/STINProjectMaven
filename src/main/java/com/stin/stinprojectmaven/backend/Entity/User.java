package com.stin.stinprojectmaven.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode


@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Integer code;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String email, String password, String first_name, String last_name, Integer code) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.code = code;
    }

    public User() {
    }
}
