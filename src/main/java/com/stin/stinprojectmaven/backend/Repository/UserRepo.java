package com.stin.stinprojectmaven.backend.Repository;

import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Entity.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String email);

    public User findByEmail(String email);
}
