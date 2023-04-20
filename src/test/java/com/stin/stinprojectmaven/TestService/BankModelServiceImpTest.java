package com.stin.stinprojectmaven.TestService;
import com.stin.stinprojectmaven.backend.Entity.Account;
import com.stin.stinprojectmaven.backend.Entity.Transaction;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Repository.AccountRepo;
import com.stin.stinprojectmaven.backend.Repository.TransactionRepo;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import com.stin.stinprojectmaven.backend.Service.BankModelService;
import com.stin.stinprojectmaven.backend.Service.CurrencyData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BankModelServiceImpTests {

    @Autowired
    private BankModelService bankModelService;

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private AccountRepo accountRepo;
    @MockBean
    private TransactionRepo transactionRepo;
    @MockBean
    private CurrencyData currencyData;
    @MockBean
    private Principal principal;

    @Test
    void createModel_Success() {
        String email = "test@example.com";
        User user = new User();
        user.setId(9);
        user.setEmail(email);
        Account account = new Account();
        account.setId_user(user.getId());
        account.setAccount_num(5237547);
        Transaction transaction = new Transaction();
        List<Transaction> transactionList = List.of(transaction);

        when(principal.getName()).thenReturn(email);
        when(userRepo.findByEmail(email)).thenReturn(user);
        when(accountRepo.findByUserId(user.getId())).thenReturn(account);
        when(transactionRepo.findAllTransactions(account.getAccount_num())).thenReturn(transactionList);

        Model model = new ExtendedModelMap();
        bankModelService.createModel(model, principal);

        assertEquals(user, model.getAttribute("user"));
        assertEquals(account, model.getAttribute("account"));
        assertEquals(currencyData, model.getAttribute("currencyData"));
        assertEquals(transactionList, model.getAttribute("transactionList"));

        verify(principal, times(1)).getName();
        verify(userRepo, times(1)).findByEmail(email);
        verify(accountRepo, times(1)).findByUserId(user.getId());
        verify(transactionRepo, times(1)).findAllTransactions(account.getAccount_num());
    }
}
