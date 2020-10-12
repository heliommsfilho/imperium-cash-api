package com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.currencyformat;

import com.github.heliommsfilho.imperium_cash.api.domain.model.system.CurrencyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyFormatRepository extends JpaRepository<CurrencyFormat, Long> {
}
