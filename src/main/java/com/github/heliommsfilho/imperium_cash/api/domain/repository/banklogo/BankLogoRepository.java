package com.github.heliommsfilho.imperium_cash.api.domain.repository.banklogo;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BankLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankLogoRepository extends JpaRepository<BankLogo, Long> {
}
