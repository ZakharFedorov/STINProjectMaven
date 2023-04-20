package com.stin.stinprojectmaven.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity

@Data
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

    public Account(Integer account_num, Integer id_user, Double crown_balance, Double dollar_balance, Double euro_balance) {
        this.account_num = account_num;
        this.id_user = id_user;
        this.crown_balance = crown_balance;
        this.dollar_balance = dollar_balance;
        this.euro_balance = euro_balance;
    }

    public Account() {
    }
}



