package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Currency;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CurrencyRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Currency Service should")
@ExtendWith(MockitoExtension.class)
class CurrencyServiceTests {

    @Mock
    private CurrencyRepository currencyRepository;
    private CurrencyService currencyService;

    @BeforeEach
    public void init() {
        currencyService = new CurrencyService(currencyRepository);
    }

    @Test
    @DisplayName("return all Currencies")
    void returnAllCurrencies() {
        when(currencyRepository.findAll()).thenReturn(createCurrencies());
        List<Currency> currencies = currencyService.getAll();

        Assertions.assertEquals("EUR", currencies.get(0).getCode());
        Assertions.assertEquals("Euro", currencies.get(0).getName());
        Assertions.assertEquals("€", currencies.get(0).getSymbol());
        Assertions.assertEquals("BRL", currencies.get(1).getCode());
        Assertions.assertEquals("Real Brasileiro", currencies.get(1).getName());
        Assertions.assertEquals("R$", currencies.get(1).getSymbol());
    }

    private static List<Currency> createCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(GenericBuilder.build(Currency.class)
                                     .with(c -> c.setCode("EUR"))
                                     .with(c -> c.setName("Euro"))
                                     .with(c -> c.setSymbol("€"))
                                     .get());

        currencies.add(GenericBuilder.build(Currency.class)
                                     .with(c -> c.setCode("BRL"))
                                     .with(c -> c.setName("Real Brasileiro"))
                                     .with(c -> c.setSymbol("R$"))
                                     .get());

        return currencies;
    }
}
