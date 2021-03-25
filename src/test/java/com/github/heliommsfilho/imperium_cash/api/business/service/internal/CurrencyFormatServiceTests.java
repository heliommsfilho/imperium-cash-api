package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CurrencyFormatRepository;
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
@DisplayName("Currency Format Service should")
@ExtendWith(MockitoExtension.class)
class CurrencyFormatServiceTests {

    @Mock
    private CurrencyFormatRepository currencyFormatRepository;
    private CurrencyFormatService currencyFormatService;

    @BeforeEach
    public void init() {
        currencyFormatService = new CurrencyFormatService(currencyFormatRepository);
    }

    @Test
    @DisplayName("return all Currency Formats")
    void returnAllCurrencyFormats() {
        when(currencyFormatRepository.findAll()).thenReturn(createCurrencyFormats());
        List<CurrencyFormat> currencyFormats = currencyFormatService.getAll();

        Assertions.assertEquals(2, currencyFormats.size());

        Assertions.assertEquals("$_###,##0.00", currencyFormats.get(0).getPattern());
        Assertions.assertEquals("$_123,456.78", currencyFormats.get(0).getExamplePositive());
        Assertions.assertEquals("$_-123,456.78", currencyFormats.get(0).getExampleNegative());
        Assertions.assertEquals("##,##0.00_$", currencyFormats.get(1).getPattern());
        Assertions.assertEquals("123,456.78_$", currencyFormats.get(1).getExamplePositive());
        Assertions.assertEquals("-123,456.78_$", currencyFormats.get(1).getExampleNegative());
    }

    private static List<CurrencyFormat> createCurrencyFormats() {
        List<CurrencyFormat> currencyFormats = new ArrayList<>();
        currencyFormats.add(GenericBuilder.build(CurrencyFormat.class)
                                          .with(c -> c.setPattern("$_###,##0.00"))
                                          .with(c -> c.setExamplePositive("$_123,456.78"))
                                          .with(c -> c.setExampleNegative("$_-123,456.78"))
                                          .get());

        currencyFormats.add(GenericBuilder.build(CurrencyFormat.class)
                                          .with(c -> c.setPattern("##,##0.00_$"))
                                          .with(c -> c.setExamplePositive("123,456.78_$"))
                                          .with(c -> c.setExampleNegative("-123,456.78_$"))
                                          .get());

        return currencyFormats;
    }
}
