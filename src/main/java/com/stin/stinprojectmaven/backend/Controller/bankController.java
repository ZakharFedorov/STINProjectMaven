package com.stin.stinprojectmaven.backend.Controller;

import com.stin.stinprojectmaven.backend.Entity.Account;
import com.stin.stinprojectmaven.backend.Entity.User;
import com.stin.stinprojectmaven.backend.Repository.AccountRepo;
import com.stin.stinprojectmaven.backend.Repository.UserRepo;
import com.stin.stinprojectmaven.backend.Service.CurrencyData;
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
    private UserRepo userRepo;
    @Autowired
    private AccountRepo AccountRepo;
    @Autowired
    private CurrencyData currencyData;
    @Autowired
    private MoneyService moneyService;


    @GetMapping("/")
    public String index(Principal principal) {
        try {
            String euser = principal.getName();
            User user = userRepo.findByEmail(euser);
            if (!user.getVerified()) {
                return "redirect:/login";
            }
        } catch (NullPointerException e) {
            return "redirect:/login";
        }


        return "bank";
    }

    @ModelAttribute
    public void CreateModel(Model model, Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        Account account = AccountRepo.findByUserId(user.getId());
        model.addAttribute("account", account);
        model.addAttribute("currencyData", currencyData);
    }

    @PostMapping(value = "/transactions", params = "action=add")
    public String Add(@RequestParam String amount,
                      @RequestParam String currencyFrom,
                      @RequestParam String currencyTo, Model model) {
        Account account = (Account) model.getAttribute("account");

        if(moneyService.checkIfWithTransfer(currencyFrom, currencyTo)){
            if (moneyService.checkIfBalanceExists(account, currencyTo)) {
                account = moneyService.addMoney(account, Double.parseDouble(amount), currencyFrom, currencyTo);
            }
        }else{
            if (moneyService.checkIfBalanceExists(account, currencyTo)) {
                account = moneyService.addMoney(account, Double.parseDouble(amount), currencyFrom);
            }
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

        if(moneyService.checkIfWithTransfer(currencyFrom, currencyTo)){
            if((moneyService.checkIfEnoughMoney(account, Double.parseDouble(amount), currencyFrom, currencyTo)) && (moneyService.checkIfBalanceExists(account, currencyTo))){
                account = moneyService.payMoney(account, Double.parseDouble(amount), currencyFrom, currencyTo);
            }
        }else{
            if((moneyService.checkIfEnoughMoney(account, Double.parseDouble(amount), currencyFrom)) && (moneyService.checkIfBalanceExists(account, currencyTo))){
                account = moneyService.payMoney(account, Double.parseDouble(amount), currencyFrom);
            }
        }

        assert account != null;
        AccountRepo.save(account);

        return "redirect:/";
    }

}
