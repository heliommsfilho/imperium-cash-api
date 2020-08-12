package com.github.heliommsfilho.imperium_cash.api.repository.currency;

import com.github.heliommsfilho.imperium_cash.api.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
