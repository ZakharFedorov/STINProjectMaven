package com.stin.stinprojectmaven.backend.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "account_num")
    private int account_num;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
