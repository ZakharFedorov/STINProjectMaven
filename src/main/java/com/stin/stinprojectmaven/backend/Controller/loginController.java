package com.stin.stinprojectmaven.backend.Controller;

import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class loginController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String index() {
        return "login";
    }
}
