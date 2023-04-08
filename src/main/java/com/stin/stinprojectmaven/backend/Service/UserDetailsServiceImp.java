package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Entity.UserCustom;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return new UserCustom(user);
        }
        throw new UsernameNotFoundException("User not available: "+ email);
    }
}
