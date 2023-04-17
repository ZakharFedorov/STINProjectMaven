package com.stin.stinprojectmaven.backend.Service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyData {

    private static final String CNBURL = "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt;jsessionid=99899C03A83B06837ABFE32DBAA25279?date=";

    public static String getCurrentlyDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    }

    public String getActualURL(String date) {
        return CNBURL + date;
    }

    public static List<String> getCurrencyData(String ActualURL) {
        List<String> list = new ArrayList<>();
        try {
            URL url = new URL(ActualURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }

        return list;
    }

    public static String getCurrencyValue(String currency, List<String> list) {
        String value = "";
        for (String line : list) {
            if (line.contains(currency)) {
                String[] split = line.split("\\|");
                value = split[4].replace(",", ".");
            }
        }
        return value;
    }

    public String getEuro() {
        return (getCurrencyValue("EUR", getCurrencyData(getActualURL(getCurrentlyDate()))));
    }

    public String getDollar() {
        return (getCurrencyValue("USD", getCurrencyData(getActualURL(getCurrentlyDate()))));
    }


    public Double getExchangeRate(String from_currency, String to_currency) {
        if (from_currency.equals("EUR") && to_currency.equals("USD")) {
            return Double.parseDouble(getDollar()) / Double.parseDouble(getEuro());
        } else if (from_currency.equals("USD") && to_currency.equals("EUR")) {
            return Double.parseDouble(getEuro()) / Double.parseDouble(getDollar());
        } else if (from_currency.equals("EUR") && to_currency.equals("CZK")) {
            return Double.parseDouble(getEuro());
        } else if (from_currency.equals("CZK") && to_currency.equals("EUR")) {
            return 1 / Double.parseDouble(getEuro());
        } else if (from_currency.equals("USD") && to_currency.equals("CZK")) {
            return Double.parseDouble(getDollar());
        } else if (from_currency.equals("CZK") && to_currency.equals("USD")) {
            return 1 / Double.parseDouble(getDollar());
        } else {
            return 0.0;
        }
    }
}
