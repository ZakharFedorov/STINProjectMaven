package com.stin.stinprojectmaven.TestEntity;

import com.stin.stinprojectmaven.backend.Entity.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Transaction.class)
public class TransactionTest {
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        transaction = new Transaction();
        transaction.setAccount_num(5236547);
        transaction.setId(1);
        transaction.setDate("2021-05-05");
        transaction.setAmount(100.0);
        transaction.setCurrency("CZK");
        transaction.setDescription("Test");
    }

    @Test
    void testConstructor() {
        assertEquals(5236547, transaction.getAccount_num());
        assertEquals(1, transaction.getId());
        assertEquals("2021-05-05", transaction.getDate());
        assertEquals(100.0, transaction.getAmount());
        assertEquals("CZK", transaction.getCurrency());
        assertEquals("Test", transaction.getDescription());

        assertEquals("Transaction(id=1, account_num=5236547, amount=100.0, currency=CZK, date=2021-05-05, description=Test)",
                transaction.toString());
    }

    @Test
    void ConstructorTest() {
        Transaction transaction2 = new Transaction(5236547, 100.0, "CZK", "Test");
        transaction2.setId(1);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        transaction.setDate(myDateObj.format(myFormatObj));
        assertEquals(transaction, transaction2);
    }

    @Test
    void testHashCode() {
        Transaction transaction2 = new Transaction(5236547, 100.0, "CZK", "Test");
        transaction2.setId(1);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        transaction.setDate(myDateObj.format(myFormatObj));
        assertEquals(transaction.hashCode(), transaction2.hashCode());
    }

//    @Test
//    public void testEntityAndTable() {
//        Entity entity = Transaction.class.getAnnotation(Entity.class);
//        Table table = Transaction.class.getAnnotation(Table.class);
//        assertNotNull(entity, "Transaction class should have @Entity annotation");
//        assertNotNull(table, "Transaction class should have @Table annotation");
//        assertEquals("transactions", table.name(), "Table name should be 'transactions'");
//    }
}
