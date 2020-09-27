package com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.accounttype;

import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
