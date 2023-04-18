package com.stin.stinprojectmaven.backend.Repository;

import com.stin.stinprojectmaven.backend.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    @Query(value = "select * from transactions where account_num = ?", nativeQuery = true)
    List<Transaction> findAllTransactions(Integer account_num);


}
