package com.stin.stinprojectmaven;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class StinProjectMavenApplicationTests {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("13fergg23r5h43r1fv23"));
    }

    @Test
    void contextLoads() {
    }

}
