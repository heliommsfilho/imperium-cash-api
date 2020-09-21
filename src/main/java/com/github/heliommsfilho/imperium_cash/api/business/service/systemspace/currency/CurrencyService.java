package com.github.heliommsfilho.imperium_cash.api.business.service.systemspace.currency;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Currency;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.currency.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }
}
