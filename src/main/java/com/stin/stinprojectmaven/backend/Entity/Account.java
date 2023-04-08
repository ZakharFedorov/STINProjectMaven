package com.stin.stinprojectmaven.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "account_num")
    private int account_num;
    @Column(name = "crown")
    private float crown;

    @Column(name = "dollar")
    private float dollar;

    @Column(name = "euro")
    private float euro;

    @Column(name = "id_user")
    private int id_user;
}
