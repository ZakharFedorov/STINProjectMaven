package com.stin.stinprojectmaven.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_num;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    private Double crown_balance;
    private Double dollar_balance;
    private Double euro_balance;

}
