package com.stin.stinprojectmaven.backend.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class bankController {

    @GetMapping("/")
    public String index() {
        return "bank";
    }
}
