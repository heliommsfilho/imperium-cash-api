package com.github.heliommsfilho.imperium_cash.api.service;

import com.github.heliommsfilho.imperium_cash.api.model.Currency;
import com.github.heliommsfilho.imperium_cash.api.repository.currency.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTests {

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    public void currencyGetAll_shoudReturnCurrencyList() {
        when(currencyRepository.findAll()).thenReturn(getMockedCurrencyList());
        Assertions.assertFalse(currencyRepository.findAll().isEmpty());
        verify(currencyRepository).findAll();
    }

    private List<Currency> getMockedCurrencyList() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency());
        currencies.get(0).setName("Euro");
        currencies.get(0).setCode("EUR");
        currencies.get(0).setSymbol("€");

        currencies.add(new Currency());
        currencies.get(1).setName("Real Brasileiro");
        currencies.get(1).setCode("BRL");
        currencies.get(1).setSymbol("R$");

        return currencies;
    }
}
