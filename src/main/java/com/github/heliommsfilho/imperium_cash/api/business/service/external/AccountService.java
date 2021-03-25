package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.AccountInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.AccountRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(AccountInput createDTO) {
        Account account = EntityDTOHelper.getInstance().map(createDTO, Account.class);
        account.setId(null);
        account.setDisabled(false);

        return accountRepository.save(account);
    }
}
