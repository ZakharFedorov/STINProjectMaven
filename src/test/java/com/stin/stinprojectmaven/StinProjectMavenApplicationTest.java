package com.stin.stinprojectmaven;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureWebMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest(classes = StinProjectMavenApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class StinProjectMavenApplicationTests {

    @Autowired
    private StinProjectMavenApplication application;

    @Test
    void contextLoads() {
        assertNotNull(application);
    }
}
