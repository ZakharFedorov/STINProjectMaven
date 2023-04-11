package com.stin.stinprojectmaven.backend.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private int id;

    private int amount;

    private String currency;

    private int account_num;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
