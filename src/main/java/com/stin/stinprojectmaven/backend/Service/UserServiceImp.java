package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Entity.UserCustom;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncode.encode(user.getPassword()));

        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
