package com.github.heliommsfilho.imperium_cash.api.repository.accounttype;

import com.github.heliommsfilho.imperium_cash.api.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
