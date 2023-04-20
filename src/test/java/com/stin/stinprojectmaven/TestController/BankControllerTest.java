package com.stin.stinprojectmaven.TestController;

import com.stin.stinprojectmaven.backend.Controller.bankController;
import com.stin.stinprojectmaven.backend.Repository.AccountRepo;
import com.stin.stinprojectmaven.backend.Repository.TransactionRepo;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import com.stin.stinprojectmaven.backend.Service.BankModelServiceImp;
import com.stin.stinprojectmaven.backend.Service.MoneyService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DirtiesContext
@ActiveProfiles("test")
public class BankControllerTest {

    @Test
    public void testIndex() {
        bankController controller = new bankController();
        String result = controller.index();
        assertEquals("bank", result);
    }

//    @Test
//    public void testCreateModel() {
//        Model model = mock(Model.class);
//        Principal principal = mock(Principal.class);
//
//        when(principal.getName()).thenReturn("test@test.com");
//        when(userRepo.findByEmail("test@test.com")).thenReturn(new User());
//        when(accountRepo.findByUserId(notNull(Integer.class))).thenReturn(new Account());
//        when(transactionRepo.findAllTransactions(1737865)).thenReturn(new ArrayList<>());
//
//        bankModelService.createModel(model, principal);
//
//        verify(model, times(1)).addAttribute(eq("user"), any(User.class));
//        verify(model, times(1)).addAttribute(eq("account"), any(Account.class));
//        verify(model, times(1)).addAttribute(eq("currencyData"), any(CurrencyData.class));
//        verify(model, times(1)).addAttribute(eq("transactionList"), anyList());
//    }

//    @Test
//    public void testAdd() throws Exception {
//        Account account = new Account();
//        when(moneyService.checkIfWithTransfer(anyString(), anyString())).thenReturn(true);
//        when(moneyService.checkIfBalanceExists(any(Account.class), anyString())).thenReturn(true);
//        when(moneyService.addMoney(any(Account.class), anyDouble(), anyString(), anyString())).thenReturn(account);
//
//        mockMvc.perform(post("/transactions")
//                        .param("action", "add")
//                        .param("amount", "100")
//                        .param("currencyFrom", "USD")
//                        .param("currencyTo", "EUR"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        verify(moneyService, times(1)).checkIfWithTransfer("USD", "EUR");
//        verify(moneyService, times(1)).checkIfBalanceExists(account, "EUR");
//        verify(moneyService, times(1)).addMoney(account, 100.0, "USD", "EUR");
//    }

//    @Test
//    public void testPay() throws Exception {
//        Account account = new Account();
//        when(moneyService.checkIfWithTransfer(anyString(), anyString())).thenReturn(true);
//        when(moneyService.checkIfEnoughMoney(any(Account.class), anyDouble(), anyString(), anyString())).thenReturn(true);
//        when(moneyService.checkIfBalanceExists(any(Account.class), anyString())).thenReturn(true);
//        when(moneyService.payMoney(any(Account.class), anyDouble(), anyString(), anyString())).thenReturn(account);
//
//        mockMvc.perform(post("/transactions")
//                        .param("action", "pay")
//                        .param("amount", "100")
//                        .param("currencyFrom", "USD")
//                        .param("currencyTo", "EUR"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//
//        verify(moneyService, times(1)).checkIfWithTransfer("USD", "EUR");
//        verify(moneyService, times(1)).checkIfEnoughMoney(account, 100.0, "USD", "EUR");
//        verify(moneyService, times(1)).checkIfBalanceExists(account, "EUR");
//        verify(moneyService, times(1)).payMoney(account, 100.0, "USD", "EUR");
//    }

}
