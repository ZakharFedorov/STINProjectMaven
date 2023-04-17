package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.EmailDetails;
import com.stin.stinprojectmaven.backend.Entity.User;

public interface EmailService {
    String sendVerificationToEmail(EmailDetails emailDetails);

    void generateEmail(User user, EmailDetails emailDetails);
}
