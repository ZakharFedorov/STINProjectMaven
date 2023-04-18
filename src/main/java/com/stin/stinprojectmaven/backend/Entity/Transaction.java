package com.stin.stinprojectmaven.backend.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer account_num;
    private Double amount;
    private String currency;
    private String date;
    private String description;

    public Transaction (Integer account_num, Double amount, String currency, String description) {
        this.account_num = account_num;
        this.amount = amount;
        this.currency = currency;
        this.description = description;

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        this.date = myDateObj.format(myFormatObj);
    }

    public Transaction() {
    }
}
