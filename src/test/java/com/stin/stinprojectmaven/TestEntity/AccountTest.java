package com.stin.stinprojectmaven.TestEntity;

import com.stin.stinprojectmaven.backend.Entity.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Account.class)
public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccount_num(5236547);
        account.setId_user(54);
        account.setEuro_balance(0.0);
        account.setDollar_balance(0.0);
        account.setCrown_balance(0.0);
    }

    @Test
    void testConstructor() {
        assertEquals(5236547, account.getAccount_num());
        assertEquals(54, account.getId_user());
        assertEquals(0.0, account.getEuro_balance());
        assertEquals(0.0, account.getDollar_balance());
        assertEquals(0.0, account.getCrown_balance());

        assertEquals("Account(account_num=5236547, id_user=54, crown_balance=0.0, dollar_balance=0.0, euro_balance=0.0)",
                account.toString());
    }

    @Test
    void testHashCode() {
        Account account2 = new Account();
        account2.setAccount_num(5236547);
        account2.setId_user(54);
        account2.setEuro_balance(0.0);
        account2.setDollar_balance(0.0);
        account2.setCrown_balance(0.0);
        assertEquals(account.hashCode(), account2.hashCode());
    }


//    @Test
//    public void testEntityAndTable() {
//        Entity entity = Account.class.getAnnotation(Entity.class);
//        Table table = Account.class.getAnnotation(Table.class);
//        assertNotNull(entity, "User class should have @Entity annotation");
//        assertNotNull(table, "User class should have @Table annotation");
//        assertEquals("accounts", table.name(), "Table name should be 'accounts'");
//    }

}
