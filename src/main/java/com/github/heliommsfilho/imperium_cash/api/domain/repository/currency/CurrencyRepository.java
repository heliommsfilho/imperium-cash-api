package com.github.heliommsfilho.imperium_cash.api.domain.repository.currency;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
