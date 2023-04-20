package com.stin.stinprojectmaven.TestEntity;

import com.stin.stinprojectmaven.backend.Entity.EmailDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailDetailsTest {

    private EmailDetails emailDetails;

    @BeforeEach
    public void setUp() {
        emailDetails = new EmailDetails();
        emailDetails.setMessage("test");
        emailDetails.setSubject("test");
        emailDetails.setToAddress("test");
    }


    @Test
    void testConstructor() {
        assertEquals("test", emailDetails.getToAddress());
        assertEquals("test", emailDetails.getSubject());
        assertEquals("test", emailDetails.getMessage());
        assertEquals("EmailDetails(toAddress=test, subject=test, message=test)",
                emailDetails.toString());
    }

    @Test
    void testHashCode() {
        EmailDetails emailDetails2 = new EmailDetails();
        emailDetails2.setMessage("test");
        emailDetails2.setSubject("test");
        emailDetails2.setToAddress("test");
        assertEquals(emailDetails.hashCode(), emailDetails2.hashCode());
    }

    @Test
    void testAddCodeToMessage() {
        EmailDetails emailDetails = new EmailDetails("test", "test", "test");
        emailDetails.addCodeToMessage(1234);
        assertEquals("test 1234", emailDetails.getMessage());
    }

    @Test
    void testEquals() {
        EmailDetails emailDetails = new EmailDetails("test", "test", "test");
        EmailDetails emailDetails1 = new EmailDetails("test", "test", "test");
        assertEquals(emailDetails, emailDetails1);
        assertEquals(emailDetails.getMessage(), emailDetails1.getMessage());
        assertEquals(emailDetails.getToAddress(), emailDetails1.getToAddress());
        assertEquals(emailDetails.getSubject(), emailDetails1.getSubject());
    }

}
