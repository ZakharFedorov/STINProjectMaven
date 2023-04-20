package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.Account;

public interface MoneyService {

    Account addMoney(Account account, Double amount, String currencyFrom, String currencyTo);
    Account payMoney(Account account, Double amount, String currencyFrom, String currencyTo);
    Boolean checkIfEnoughMoney(Account account, Double amount, String currencyFrom, String currencyTo);
    Boolean checkIfBalanceExists(Account account, String currency);

}
