package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.Account;
import com.stin.stinprojectmaven.backend.Entity.Transaction;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Repository.AccountRepo;
import com.stin.stinprojectmaven.backend.Repository.TransactionRepo;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

@Service
public class BankModelServiceImp implements BankModelService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CurrencyData currencyData;

    @Override
    public void createModel(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        Account account = accountRepo.findByUserId(user.getId());
        model.addAttribute("account", account);
        model.addAttribute("currencyData", currencyData);
        List<Transaction> transactionList = transactionRepo.findAllTransactions(account.getAccount_num());
        model.addAttribute("transactionList", transactionList);
    }
}
