package com.stin.stinprojectmaven.backend.Repository;

import com.stin.stinprojectmaven.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import  org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {
   @Query(value = "select * from users where email = ?", nativeQuery = true)
    User findByEmail(String email);

    @Modifying
    @Query(value = "update users set code = ?1 where email = ?2", nativeQuery = true)
    void updateUserCode(int code, String email);
}
