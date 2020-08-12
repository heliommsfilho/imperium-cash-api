package com.github.heliommsfilho.imperium_cash.api.repository.currency.format;

import com.github.heliommsfilho.imperium_cash.api.model.CurrencyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyFormatRepository extends JpaRepository<CurrencyFormat, Long> {
}
