package com.stin.stinprojectmaven.TestEntity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Entity.UserCustom;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserCustom.class)
@ContextConfiguration(classes = {UserCustom.class})
public class UserCustomTest {


    @MockBean
    private User user;

    @Test
    void getPassword() {
        UserCustom customUserDetails = new UserCustom(user);
        assertNull(customUserDetails.getPassword());
    }

    @Test
    void getUsername() {
        UserCustom customUserDetails = new UserCustom(user);
        assertNull(customUserDetails.getUsername());
    }

    @Test
    void getAuthorities() {
        UserCustom customUserDetails = new UserCustom(user);
        assertNull(customUserDetails.getAuthorities());
    }

    @Test
    void isAccountNonExpired() {
        UserCustom customUserDetails = new UserCustom(user);
        assertTrue(customUserDetails.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        UserCustom customUserDetails = new UserCustom(user);
        assertTrue(customUserDetails.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        UserCustom customUserDetails = new UserCustom(user);
        assertTrue(customUserDetails.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        UserCustom customUserDetails = new UserCustom(user);
        assertTrue(customUserDetails.isEnabled());
    }
}
