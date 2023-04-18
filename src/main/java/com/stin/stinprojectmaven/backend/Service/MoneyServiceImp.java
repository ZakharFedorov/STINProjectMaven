package com.stin.stinprojectmaven.backend.Service;

import com.stin.stinprojectmaven.backend.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImp implements MoneyService {

    @Autowired
    private CurrencyData currencyData;

    @Override
    public Boolean checkIfWithTransfer(String currencyFrom, String currencyTo) {
        return !currencyFrom.equals(currencyTo);
    }

    @Override
    public Account addMoney(Account account, Double amount, String currencyFrom) {
        switch (currencyFrom) {
            case "USD" -> {
                System.out.println("USD");
                account.setDollar_balance(account.getDollar_balance() + amount);
            }
            case "EUR" -> {
                System.out.println("EUR");
                account.setEuro_balance(account.getEuro_balance() + amount);
            }
            case "CZK" -> {
                System.out.println("CZK");
                account.setCrown_balance(account.getCrown_balance() + amount);
            }
        }

        return account;
    }

    @Override
    public Account addMoney(Account account, Double amount, String currencyFrom, String currencyTo) {
        switch (currencyFrom) {
            case "USD" -> {
                switch (currencyTo) {
                    case "EUR" -> account.setEuro_balance(account.getEuro_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    case "CZK" -> account.setCrown_balance(account.getCrown_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    default -> {
                        return null;
                    }
                }
            }
            case "EUR" -> {
                switch (currencyTo) {
                    case "USD" -> account.setDollar_balance(account.getDollar_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    case "CZK" -> account.setCrown_balance(account.getCrown_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    default -> {
                        return null;
                    }
                }
            }
            case "CZK" -> {
                switch (currencyTo) {
                    case "USD" -> account.setDollar_balance(account.getDollar_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    case "EUR" -> account.setEuro_balance(account.getEuro_balance() + (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    default -> {
                        return null;
                    }
                }
            }
        }

        return account;
    }

    @Override
    public Account payMoney(Account account, Double amount, String currencyFrom) {
        switch (currencyFrom) {
            case "USD" -> account.setDollar_balance(account.getDollar_balance() - amount);
            case "EUR" -> account.setEuro_balance(account.getEuro_balance() - amount);
            case "CZK" -> account.setCrown_balance(account.getCrown_balance() - amount);
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
                    case "EUR" -> account.setEuro_balance(account.getEuro_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    case "CZK" -> account.setCrown_balance(account.getCrown_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    default -> {
                        return null;
                    }
                }
            }
            case "EUR" -> {
                switch (currencyTo) {
                    case "USD" -> account.setDollar_balance(account.getDollar_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    case "CZK" -> account.setCrown_balance(account.getCrown_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    default -> {
                        return null;
                    }
                }
            }
            case "CZK" -> {
                switch (currencyTo) {
                    case "USD" -> account.setDollar_balance(account.getDollar_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
                    case "EUR" -> account.setEuro_balance(account.getEuro_balance() - (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount));
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
    public Boolean checkIfEnoughMoney(Account account, Double amount, String currencyFrom) {
        switch (currencyFrom) {
            case "USD" -> {
                if (account.getDollar_balance() >= amount) {
                    return true;
                }
            }
            case "EUR" -> {
                if (account.getEuro_balance() >= amount) {
                    return true;
                }
            }
            case "CZK" -> {
                if (account.getCrown_balance() >= amount) {
                    return true;
                }
            }
            default -> {
                return false;
            }
        }

        return false;
    }

    @Override
    public Boolean checkIfEnoughMoney(Account account, Double amount, String currencyFrom, String currencyTo) {
        switch (currencyFrom) {
            case "USD" -> {
                switch (currencyTo) {
                    case "EUR", "CZK" -> {
                        if (account.getDollar_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
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
                    case "USD", "CZK" -> {
                        if (account.getEuro_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
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
                    case "USD", "EUR" -> {
                        if (account.getCrown_balance() >= (currencyData.getExchangeRate(currencyFrom, currencyTo) * amount)) {
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


}
