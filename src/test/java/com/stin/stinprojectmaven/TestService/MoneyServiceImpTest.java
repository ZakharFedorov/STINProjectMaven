package com.stin.stinprojectmaven.TestService;

import com.stin.stinprojectmaven.backend.Entity.Account;
import com.stin.stinprojectmaven.backend.Service.CurrencyData;
import com.stin.stinprojectmaven.backend.Service.MoneyServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoneyServiceImpTest {

    private MoneyServiceImp moneyService;
    private CurrencyData currencyData;

    @BeforeEach
    public void setUp() {
        moneyService = new MoneyServiceImp();
        currencyData = Mockito.mock(CurrencyData.class);
        moneyService.setCurrencyData(currencyData); // Inject the mocked CurrencyData instance
    }

    @Test
    void setCurrencyDataTest() {
        moneyService.setCurrencyData(currencyData);
        assertNotNull(moneyService.getCurrencyData());
    }

    @Test
    void testAddMoney() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "USD", "USD");
        assertEquals(150.0, updatedAccount.getDollar_balance());
    }

    @Test
    public void addMoneyUSDTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "USD", "USD");

        assertEquals(150.0, updatedAccount.getDollar_balance(), 0.001);
    }

    @Test
    public void addMoneyEURTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "EUR", "EUR");

        assertEquals(150.0, updatedAccount.getEuro_balance(), 0.001);
    }

    @Test
    public void addMoneyCZKTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "CZK", "CZK");

        assertEquals(150.0, updatedAccount.getCrown_balance(), 0.001);
    }

    @Test
    public void addMoneyUSDtoEURTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "EUR")).thenReturn(0.8);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "USD", "EUR");

        assertEquals(140.0, updatedAccount.getEuro_balance(), 0.001);
    }

    @Test
    public void addMoneyUSDtoCZKTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "CZK")).thenReturn(22.0);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "USD", "CZK");

        assertEquals(1200.0, updatedAccount.getCrown_balance(), 0.001);
    }

    @Test
    public void addMoneyEURtoUSDTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "USD")).thenReturn(1.25);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "EUR", "USD");

        assertEquals(162.5, updatedAccount.getDollar_balance(), 0.001);
    }

    @Test
    public void addMoneyEURtoCZKTest() {
        Account account = new Account(5236547, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "CZK")).thenReturn(25.0);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "EUR", "CZK");

        assertEquals(1350.0, updatedAccount.getCrown_balance(), 0.001);
    }

    @Test
    public void addMoneyCZKtoUSDTest() {
        Account account = new Account(6342333, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "USD")).thenReturn(0.045);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "CZK", "USD");

        assertEquals(102.25, updatedAccount.getDollar_balance(), 0.001);
    }

    @Test
    public void addMoneyCZKtoEURTest() {
        Account account = new Account(6342333, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "EUR")).thenReturn(0.04);

        Account updatedAccount = moneyService.addMoney(account, 50.0, "CZK", "EUR");

        assertEquals(102.0, updatedAccount.getEuro_balance(), 0.001);
    }

    @Test
    void addMoneyToUSDNotExistingAccountTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "USD", "JYT");

        assertNull(updatedAccount);
    }

    @Test
    void addMoneyToEURNotExistingAccountTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "EUR", "JYT");

        assertNull(updatedAccount);
    }

    @Test
    void addMoneyToCZKNotExistingAccountTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "CZK", "JYT");

        assertNull(updatedAccount);
    }

    @Test
    void addMoneyToNotExistingAccountTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        Account updatedAccount = moneyService.addMoney(account, 50.0, "JYT", "JYT");

        assertNull(updatedAccount);
    }

    @Test
    void testPayMoney() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "USD","USD");
        assertEquals(50.0, updatedAccount.getDollar_balance());
    }


    @Test
    public void payMoneyUSDTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "USD" ,"USD");

        assertEquals(50.0, updatedAccount.getDollar_balance(), 0.001);
    }

    @Test
    public void payMoneyEURTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "EUR" ,"EUR");

        assertEquals(50.0, updatedAccount.getEuro_balance(), 0.001);
    }

    @Test
    public void payMoneyCZKTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "CZK" ,"CZK");

        assertEquals(50.0, updatedAccount.getCrown_balance(), 0.001);
    }

    @Test
    public void payMoneyUSDtoEURTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "EUR")).thenReturn(0.85);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "USD", "EUR");

        assertEquals(57.5, updatedAccount.getEuro_balance(), 0.001);
    }

    @Test
    public void payMoneyUSDtoCZKTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "CZK")).thenReturn(22.0);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "USD", "CZK");

        assertEquals(-1000.0, updatedAccount.getCrown_balance(), 0.001);
    }

    @Test
    public void payMoneyEURtoUSDTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "USD")).thenReturn(1.18);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "EUR", "USD");

        assertEquals(41.0, updatedAccount.getDollar_balance(), 0.001);
    }

    @Test
    public void payMoneyEURtoCZKTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "CZK")).thenReturn(25.0);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "EUR", "CZK");

        assertEquals(-1150.0, updatedAccount.getCrown_balance(), 0.001);
    }

    @Test
    public void payMoneyCZKtoUSDTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "USD")).thenReturn(0.045);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "CZK", "USD");

        assertEquals(97.75, updatedAccount.getDollar_balance(), 0.001);
    }

    @Test
    public void payMoneyCZKtoEURTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "EUR")).thenReturn(0.040);

        Account updatedAccount = moneyService.payMoney(account, 50.0, "CZK", "EUR");

        assertEquals(98.0, updatedAccount.getEuro_balance(), 0.001);
    }

    @Test
    void payMoneyCZKtoNotExistingCurrencyTest() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        assertNull(moneyService.payMoney(account, 50.0, "CZK", "JPY"));
    }

    @Test
    void payMoneyUSDtoNotExistingCurrencyTest() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        assertNull(moneyService.payMoney(account, 50.0, "USD", "JPY"));
    }

    @Test
    void payMoneyEURtoNotExistingCurrencyTest() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        assertNull(moneyService.payMoney(account, 50.0, "EUR", "JPY"));
    }

    @Test
    void payMoneyNotExistingCurrencyTest() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        assertNull(moneyService.payMoney(account, 50.0, "JPY", "JPY"));
    }

    @Test
    void testCheckIfEnoughMoney() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);

        assertTrue(moneyService.checkIfEnoughMoney(account, 50.0, "USD", "USD"));
        assertFalse(moneyService.checkIfEnoughMoney(account, 150.0, "USD", "USD"));
        assertTrue(moneyService.checkIfEnoughMoney(account, 50.0, "EUR", "EUR"));
        assertFalse(moneyService.checkIfEnoughMoney(account, 150.0, "EUR", "EUR"));
        assertTrue(moneyService.checkIfEnoughMoney(account, 50.0, "CZK", "CZK"));
        assertFalse(moneyService.checkIfEnoughMoney(account, 150.0, "CZK", "CZK"));

    }

    @Test
    public void checkIfEnoughMoneyUSDToEURTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "EUR")).thenReturn(0.85);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "USD", "EUR");

        assertTrue(result);
    }

    @Test
    public void checkIfEnoughMoneyEURToUSDTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "USD")).thenReturn(1.15);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "EUR", "USD");

        assertTrue(result);
    }

    @Test
    public void checkIfEnoughMoneyUSDToCZKTest() {
        Account account = new Account(7777777, 2, 1200.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "CZK")).thenReturn(22.0);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "USD", "CZK");

        assertTrue(result);
    }

    @Test
    public void checkIfEnoughMoneyCZKToUSDTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "USD")).thenReturn(0.045);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "CZK", "USD");

        assertTrue(result);
    }

    @Test
    public void checkIfEnoughMoneyEURToCZKTest() {
        Account account = new Account(7777777, 2, 1200.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "CZK")).thenReturn(23.50);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "EUR", "CZK");

        assertTrue(result);
    }

    @Test
    public void checkIfEnoughMoneyCZKToEURTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "EUR")).thenReturn(0.04);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "CZK", "EUR");

        assertTrue(result);
    }

    @Test
    void checkIfEnoughMoneyCZKToNotExistingCurrencyTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("CZK", "JPY")).thenReturn(0.0);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "CZK", "JPY");

        assertFalse(result);
    }

    @Test
    void checkIfEnoughMoneyUSDToNotExistingCurrencyTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("USD", "JPY")).thenReturn(0.0);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "USD", "JPY");

        assertFalse(result);
    }

    @Test
    void checkIfEnoughMoneyEURToNotExistingCurrencyTest() {
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("EUR", "JPY")).thenReturn(0.0);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "EUR", "JPY");

        assertFalse(result);
    }

    @Test
    void checkIfEnoughMoneyNotExistingCurrency(){
        Account account = new Account(7777777, 2, 100.0, 100.0, 100.0);
        when(currencyData.getExchangeRate("JPY", "JPY")).thenReturn(0.0);

        boolean result = moneyService.checkIfEnoughMoney(account, 50.0, "JPY", "JPY");

        assertFalse(result);
    }



    @Test
    void testCheckIfBalanceExists() {
        Account account = new Account();
        account.setDollar_balance(100.0);
        account.setEuro_balance(100.0);
        account.setCrown_balance(100.0);
        Account account2 = new Account();

        assertTrue(moneyService.checkIfBalanceExists(account, "USD"));
        assertTrue(moneyService.checkIfBalanceExists(account, "EUR"));
        assertTrue(moneyService.checkIfBalanceExists(account, "CZK"));
        assertFalse(moneyService.checkIfBalanceExists(account2, "USD"));

        assertFalse(moneyService.checkIfBalanceExists(account, "JPY"));
    }
}
