package com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.banklogo;

import com.github.heliommsfilho.imperium_cash.api.domain.model.system.BankLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankLogoRepository extends JpaRepository<BankLogo, Long> {
}
