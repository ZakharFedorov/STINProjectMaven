package com.stin.stinprojectmaven.backend.Controller;

import com.stin.stinprojectmaven.backend.Entity.Account;
import com.stin.stinprojectmaven.backend.Entity.Transaction;
import com.stin.stinprojectmaven.backend.Repository.AccountRepo;
import com.stin.stinprojectmaven.backend.Repository.TransactionRepo;
import com.stin.stinprojectmaven.backend.Service.BankModelService;
import com.stin.stinprojectmaven.backend.Service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class bankController {

    @Autowired
    private AccountRepo AccountRepo;
    @Autowired
    private TransactionRepo TransactionRepo;
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private BankModelService bankModelService;

    @ModelAttribute
    public void createModel(Model model, Principal principal) {
        bankModelService.createModel(model, principal);
    }

    @GetMapping("/")
    public String index() {
        return "bank";
    }

    @PostMapping(value = "/transactions", params = "action=add")
    public String Add(@RequestParam String amount,
                      @RequestParam String currencyFrom,
                      @RequestParam String currencyTo, Model model) {
        Account account = (Account) model.getAttribute("account");

        if (moneyService.checkIfBalanceExists(account, currencyTo)) {
            account = moneyService.addMoney(account, Double.parseDouble(amount), currencyFrom, currencyTo);
            Transaction transaction = new Transaction(account.getAccount_num(), Double.parseDouble(amount), currencyFrom, "Deposit");
            TransactionRepo.save(transaction);
        }

        assert account != null;
        AccountRepo.save(account);

        return "redirect:/";
    }

    @PostMapping(value = "/transactions", params = "action=pay")
    public String Pay(@RequestParam String amount,
                      @RequestParam String currencyFrom,
                      @RequestParam String currencyTo, Model model) {
        Account account = (Account) model.getAttribute("account");

        if((moneyService.checkIfBalanceExists(account, currencyTo)) && (moneyService.checkIfEnoughMoney(account, Double.parseDouble(amount), currencyFrom, currencyTo))){
            account = moneyService.payMoney(account, Double.parseDouble(amount), currencyFrom, currencyTo);
            Transaction transaction = new Transaction(account.getAccount_num(), Double.parseDouble(amount), currencyFrom, "Payment");
            TransactionRepo.save(transaction);
        }

        assert account != null;
        AccountRepo.save(account);


        return "redirect:/";
    }

}
