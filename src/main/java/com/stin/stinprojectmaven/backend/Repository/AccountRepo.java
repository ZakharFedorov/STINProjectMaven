package com.stin.stinprojectmaven.backend.Repository;

import com.stin.stinprojectmaven.backend.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account, Integer> {
    @Query(value = "select * from accounts where id_user = ?", nativeQuery = true)
    Account findByUserId(Integer id_user);
}
