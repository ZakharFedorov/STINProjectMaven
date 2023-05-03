package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImp implements MoneyService {

    @Autowired
    CurrencyData currencyData;


    @Override
    public Account addMoney(Account account, Double amount, String currencyFrom, String currencyTo) {
        switch (currencyFrom) {
            case "USD" -> {
                switch (currencyTo) {
                    case "USD" -> account.setDollar_balance(Math.round((account.getDollar_balance() + amount)*100.0)/100.0);
                    case "EUR" -> account.setEuro_balance(Math.round((account.getEuro_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    case "CZK" -> account.setCrown_balance(Math.round((account.getCrown_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    default -> {
                        return null;
                    }
                }
            }
            case "EUR" -> {
                switch (currencyTo) {
                    case "EUR" -> account.setEuro_balance(account.getEuro_balance() + amount);
                    case "USD" -> account.setDollar_balance(Math.round((account.getDollar_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    case "CZK" -> account.setCrown_balance(Math.round((account.getCrown_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    default -> {
                        return null;
                    }
                }
            }
            case "CZK" -> {
                switch (currencyTo) {
                    case "CZK" -> account.setCrown_balance(account.getCrown_balance() + amount);
                    case "USD" -> account.setDollar_balance(Math.round((account.getDollar_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    case "EUR" -> account.setEuro_balance(Math.round((account.getEuro_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    default -> {
                        return null;
                    }
                }
            }
            default -> {
                return null;
            }
        }

        return account;
    }

    @Override
    public Account payMoney(Account account, Double amount, String currencyFrom, String currencyTo) {
        switch (currencyFrom) {
            case "USD" -> {
                switch (currencyTo) {
                    case "USD" -> account.setDollar_balance(account.getDollar_balance() - amount);
                    case "EUR" -> account.setEuro_balance(Math.round((account.getEuro_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    case "CZK" -> account.setCrown_balance(Math.round((account.getCrown_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    default -> {
                        return null;
                    }
                }
            }
            case "EUR" -> {
                switch (currencyTo) {
                    case "EUR" -> account.setEuro_balance(account.getEuro_balance() - amount);
                    case "USD" -> account.setDollar_balance(Math.round((account.getDollar_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    case "CZK" -> account.setCrown_balance(Math.round((account.getCrown_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    default -> {
                        return null;
                    }
                }
            }
            case "CZK" -> {
                switch (currencyTo) {
                    case "CZK" -> account.setCrown_balance(account.getCrown_balance() - amount);
                    case "USD" -> account.setDollar_balance(Math.round((account.getDollar_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    case "EUR" -> account.setEuro_balance(Math.round((account.getEuro_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount))*1000.0)/1000.0);
                    default -> {
                        return null;
                    }
                }
            }
            default -> {
                return null;
            }
        }
        return account;
    }

    @Override
    public Boolean checkIfEnoughMoney(Account account, Double amount, String currencyFrom, String currencyTo) {
        switch (currencyFrom) {
            case "USD" -> {
                switch (currencyTo) {
                    case "USD" -> {
                        if (account.getDollar_balance() >= amount) {
                            return true;
                        }
                    }
                    case "CZK" -> {
                        if (account.getCrown_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
                            return true;
                        }
                    }
                    case "EUR" -> {
                        if (account.getEuro_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
                            return true;
                        }
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case "EUR" -> {
                switch (currencyTo) {
                    case "EUR" -> {
                        if (account.getEuro_balance() >= amount) {
                            return true;
                        }
                    }
                    case "CZK" -> {
                        if (account.getCrown_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
                            return true;
                        }
                    }
                    case "USD" -> {
                        if (account.getDollar_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
                            return true;
                        }
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case "CZK" -> {
                switch (currencyTo) {
                    case "CZK" -> {
                        if (account.getCrown_balance() >= amount) {
                            return true;
                        }
                    }
                    case "EUR" -> {
                        if (account.getEuro_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
                            return true;
                        }
                    }
                    case "USD" -> {
                        if (account.getDollar_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
                            return true;
                        }
                    }
                    default -> {
                        return false;
                    }
                }
            }
            default -> {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean checkIfBalanceExists(Account account, String currency) {
        switch (currency) {
            case "USD" -> {
                if (account.getDollar_balance() != null) {
                    return true;
                }
            }
            case "EUR" -> {
                if (account.getEuro_balance() != null) {
                    return true;
                }
            }
            case "CZK" -> {
                if (account.getCrown_balance() != null) {
                    return true;
                }
            }
            default -> {
                return false;
            }
        }

        return false;
    }

    public void setCurrencyData(CurrencyData currencyData) {
        this.currencyData = currencyData;
    }

    public CurrencyData getCurrencyData() {
        return currencyData;
    }


}
