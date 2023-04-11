package com.stin.stinprojectmaven.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private int account_num;
    private float crown;
    private float dollar;
    private float euro;
    private int id_user;
}
