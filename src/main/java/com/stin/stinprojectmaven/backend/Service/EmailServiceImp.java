package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.EmailDetails;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Random;
@Service
public class EmailServiceImp implements EmailService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void generateEmail(User user, EmailDetails emailDetails){

        Random random = new Random();
        int randomCode = random.nextInt((9999999 - 1000000) + 1) + 1000000;
        emailDetails.addCodeToMessage(randomCode);
        System.out.println(randomCode);
        user.setCode(randomCode);
        userRepo.updateUserCode(randomCode, user.getEmail());

        sendVerificationToEmail(emailDetails);
    }

    @Override
    public String sendVerificationToEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("zakharstin@gmail.com");
            simpleMailMessage.setTo(emailDetails.getToAddress());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            simpleMailMessage.setText(emailDetails.getMessage());
            mailSender.send(simpleMailMessage);
            return "Email sent successfully";
        }catch (MailException e){
            e.printStackTrace();
            return "Error sending email";
        }
    }
}
