package com.stin.stinprojectmaven.TestService;
import com.stin.stinprojectmaven.backend.Entity.EmailDetails;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import com.stin.stinprojectmaven.backend.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmailServiceImpTests {

    @Autowired
    private EmailService emailService;

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private JavaMailSender mailSender;

    @Test
    void generateEmail_Success() {
        User user = new User();
        user.setEmail("test@example.com");
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setToAddress(user.getEmail());
        emailDetails.setSubject("Test Subject");
        emailDetails.setMessage("Test Message");

        emailService.generateEmail(user, emailDetails);

        verify(userRepo, times(1)).updateUserCode(anyInt(), eq(user.getEmail()));
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendVerificationToEmail_Success() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setToAddress("test@example.com");
        emailDetails.setSubject("Test Subject");
        emailDetails.setMessage("Test Message");

        String result = emailService.sendVerificationToEmail(emailDetails);

        assertEquals("Email sent successfully", result);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendVerificationToEmail_Failure() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setToAddress("test@example.com");
        emailDetails.setSubject("Test Subject");
        emailDetails.setMessage("Test Message");

        doThrow(MailSendException.class).when(mailSender).send(any(SimpleMailMessage.class));

        String result = emailService.sendVerificationToEmail(emailDetails);

        assertEquals("Error sending email", result);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}