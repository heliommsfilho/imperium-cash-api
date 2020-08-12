package com.github.heliommsfilho.imperium_cash.api.service;

import com.github.heliommsfilho.imperium_cash.api.model.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.repository.currency.format.CurrencyFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyFormatService {

    public CurrencyFormatRepository currencyFormatRepository;

    @Autowired
    public CurrencyFormatService(CurrencyFormatRepository currencyFormatRepository) {
        this.currencyFormatRepository = currencyFormatRepository;
    }

    public List<CurrencyFormat> getAll() {
        return currencyFormatRepository.findAll();
    }
}
