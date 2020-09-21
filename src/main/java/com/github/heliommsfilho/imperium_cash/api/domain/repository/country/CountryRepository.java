package com.github.heliommsfilho.imperium_cash.api.domain.repository.country;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    
}