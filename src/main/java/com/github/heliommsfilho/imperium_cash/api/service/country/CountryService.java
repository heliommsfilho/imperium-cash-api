package com.github.heliommsfilho.imperium_cash.api.service.country;

import java.util.List;
import java.util.Optional;

import com.github.heliommsfilho.imperium_cash.api.model.Country;
import com.github.heliommsfilho.imperium_cash.api.repository.country.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAll() {
        return this.countryRepository.findAll();
    }

    public Optional<Country> getById(Long id) {
        return this.countryRepository.findById(id);
    }
}