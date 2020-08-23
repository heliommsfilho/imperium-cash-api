package com.github.heliommsfilho.imperium_cash.api.service;

import com.github.heliommsfilho.imperium_cash.api.model.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.repository.currencyformat.CurrencyFormatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyFormatServiceTests {

    @Mock
    private CurrencyFormatRepository currencyFormatRepository;

    @Test
    void currencyFormatGetAll_shouldReturnCurrencyFormatList() {
        CurrencyFormat currencyFormat1 = new CurrencyFormat();
        currencyFormat1.setPattern("$_###,##0.00");
        currencyFormat1.setExamplePositive("$_123,456.78");
        currencyFormat1.setExampleNegative("$_-123,456.78");

        CurrencyFormat currencyFormat2 = new CurrencyFormat();
        currencyFormat2.setPattern("##,##0.00_$");
        currencyFormat2.setExamplePositive("123,456.78_$");
        currencyFormat2.setExampleNegative("-123,456.78_$");

        when(currencyFormatRepository.findAll()).thenReturn(Arrays.asList(currencyFormat1, currencyFormat2));

        List<CurrencyFormat> currencyFormatList = currencyFormatRepository.findAll();

        Assertions.assertEquals(2, currencyFormatList.size());
        Assertions.assertNotNull(currencyFormatList.get(0).getPattern());
        Assertions.assertNotNull(currencyFormatList.get(0).getExamplePositive());
        Assertions.assertNotNull(currencyFormatList.get(0).getExampleNegative());
        Assertions.assertNotNull(currencyFormatList.get(1).getPattern());
        Assertions.assertNotNull(currencyFormatList.get(1).getExamplePositive());
        Assertions.assertNotNull(currencyFormatList.get(1).getExampleNegative());
    }
}
