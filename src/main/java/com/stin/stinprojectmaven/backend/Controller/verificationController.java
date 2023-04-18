package com.stin.stinprojectmaven.backend.Controller;

import com.stin.stinprojectmaven.backend.Entity.EmailDetails;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Service.EmailService;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class verificationController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailService emailService;

    private static User user;


    @GetMapping("/verify")
    public String processRegister(Principal principal){
        String euser = principal.getName();
        user = userRepo.findByEmail(euser);
        if(user.getVerified()){
            userRepo.save(user);
            return "redirect:/";
        }
        EmailDetails toEmail = new EmailDetails(user.getEmail(), "Your Verification Code", "Hello, " + user.getFullName() + "\n\n Your verification code is: ");
        emailService.generateEmail(user, toEmail);
        return "verification";
    }

    @PostMapping("/verify")
    public String processVerify(String code){
        if(user.getCode() == Integer.parseInt(code)){
            user.setVerified(true);
            userRepo.save(user);
            return "redirect:/";
        }
        return "redirect:/verify";
    }


}
