package com.stin.stinprojectmaven.TestEntity;

import com.stin.stinprojectmaven.backend.Entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = User.class)
public class UserTest {

    @Test
    void testConstructor() {
        User user = new User();
        user.setEmail("user.user@user.com");
        user.setId(54);
        user.setFirst_name("Name");
        user.setLast_name("Surname");
        user.setPassword("userpassword");
        user.setCode(3049137);
        user.setVerified(false);
        String userToString = user.toString();
        assertEquals(54, user.getId());
        assertEquals("user.user@user.com", user.getEmail());
        assertEquals("Name", user.getFirst_name());
        assertEquals("Surname", user.getLast_name());
        assertEquals("userpassword", user.getPassword());
        assertEquals(3049137, user.getCode());
        assertFalse(user.getVerified());
        assertEquals("User(id=54, first_name=Name, last_name=Surname, email=user.user@user.com, password=userpassword, code=3049137, verified=false)",
                userToString);
    }

    @Test
    void testHashCode() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setId(54);
        user.setFirst_name("Name");
        user.setLast_name("Surname");
        user.setPassword("userpassword");
        user.setCode(3049137);
        user.setVerified(false);
        User user2 = new User();
        user2.setEmail("user@gmail.com");
        user2.setId(54);
        user2.setFirst_name("Name");
        user2.setLast_name("Surname");
        user2.setPassword("userpassword");
        user2.setCode(3049137);
        user2.setVerified(false);
        assertEquals(user.hashCode(), user2.hashCode());
    }

    @Test
    void testFullName() {
        User user = new User();
        user.setFirst_name("Name");
        user.setLast_name("Surname");
        assertEquals("Name Surname", user.getFullName());
    }

    @Test
    public void testEntityAndTable() {
        Entity entity = User.class.getAnnotation(Entity.class);
        Table table = User.class.getAnnotation(Table.class);
        assertNotNull(entity, "User class should have @Entity annotation");
        assertNotNull(table, "User class should have @Table annotation");
        assertEquals("users", table.name(), "Table name should be 'users'");
    }
}
