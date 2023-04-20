package com.stin.stinprojectmaven.TestService;
import com.stin.stinprojectmaven.backend.Service.CurrencyData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CurrencyDataTests {

    @InjectMocks
    private CurrencyData currencyData;

    @Test
    void testGetCurrentlyDate() {
        String date = CurrencyData.getCurrentlyDate();
        assertTrue(date.matches("\\d{2}\\.\\d{2}\\.\\d{4}"));
    }

    @Test
    void testGetActualURL() {
        String date = "20.04.2023";
        String actualUrl = currencyData.getActualURL(date);
        String expectedUrl = "https://www.cnb.cz/cs/financni-trhy/devizovy-trh/kurzy-devizoveho-trhu/kurzy-devizoveho-trhu/denni_kurz.txt;jsessionid=99899C03A83B06837ABFE32DBAA25279?date=20.04.2023";
        assertEquals(expectedUrl, actualUrl);
    }

    // Note: You might need to mock the web service to test this function properly.
    @Test
    void testGetCurrencyData() {
        String actualUrl = currencyData.getActualURL(CurrencyData.getCurrentlyDate());
        List<String> currencyDataList = CurrencyData.getCurrencyData(actualUrl);
        assertTrue(currencyDataList.size() > 0);
    }

    // Note: You might need to mock the web service to test this function properly.
    @Test
    void testGetCurrencyValue() {
        String actualUrl = currencyData.getActualURL(CurrencyData.getCurrentlyDate());
        List<String> currencyDataList = CurrencyData.getCurrencyData(actualUrl);
        String usdValue = CurrencyData.getCurrencyValue("USD", currencyDataList);
        assertTrue(Double.parseDouble(usdValue) > 0);
    }

    // Note: You might need to mock the web service to test these functions properly.
    @Test
    void testGetExchangeRate() {
        Double usdToEur = currencyData.getExchangeRate("USD", "EUR");
        assertTrue(usdToEur > 0);

        Double eurToUsd = currencyData.getExchangeRate("EUR", "USD");
        assertTrue(eurToUsd > 0);

        Double eurToCzk = currencyData.getExchangeRate("EUR", "CZK");
        assertTrue(eurToCzk > 0);

        Double czkToEur = currencyData.getExchangeRate("CZK", "EUR");
        assertTrue(czkToEur > 0);

        Double usdToCzk = currencyData.getExchangeRate("USD", "CZK");
        assertTrue(usdToCzk > 0);

        Double czkToUsd = currencyData.getExchangeRate("CZK", "USD");
        assertTrue(czkToUsd > 0);

        Double invalid = currencyData.getExchangeRate("INVALID", "INVALID");
        assertEquals(0.0, invalid, 0.0);
    }
}
