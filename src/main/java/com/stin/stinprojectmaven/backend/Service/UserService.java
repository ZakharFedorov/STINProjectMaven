package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.User;

public interface UserService {
    public User createUser(User user);
    public boolean checkEmail(String email);
}
