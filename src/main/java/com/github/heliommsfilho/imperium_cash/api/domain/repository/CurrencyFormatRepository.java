package com.github.heliommsfilho.imperium_cash.api.domain.repository;

import com.github.heliommsfilho.imperium_cash.api.domain.model.CurrencyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyFormatRepository extends JpaRepository<CurrencyFormat, Long> {
}
