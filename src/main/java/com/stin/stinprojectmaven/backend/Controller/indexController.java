package com.stin.stinprojectmaven.backend.Controller;

import com.stin.stinprojectmaven.backend.Service.UserService;
import com.stin.stinprojectmaven.backend.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@Controller

public class indexController {

    @Autowired
    private UserService userService;
    @Autowired
    public indexController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/bank")
    public String bank() {
        return "bank";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
