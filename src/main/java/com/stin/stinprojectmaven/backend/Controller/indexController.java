package com.stin.stinprojectmaven.backend.Controller;

import com.stin.stinprojectmaven.backend.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller

public class indexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute User user) {
        System.out.println(user.toString());
        return "index";
    }

}
