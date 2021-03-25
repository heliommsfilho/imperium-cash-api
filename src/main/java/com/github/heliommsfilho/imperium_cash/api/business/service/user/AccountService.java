package com.github.heliommsfilho.imperium_cash.api.business.service.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.account.AccountCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.account.AccountRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(AccountCreateDTO createDTO) {
        Account account = EntityDTOHelper.getInstance().map(createDTO, Account.class);
        account.setId(null);
        account.setDisabled(false);

        return accountRepository.save(account);
    }
}
