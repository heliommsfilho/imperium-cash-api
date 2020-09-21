package com.github.heliommsfilho.imperium_cash.api.business.service.systemspace.accounttype;

import com.github.heliommsfilho.imperium_cash.api.domain.model.AccountType;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.accounttype.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeService(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    public List<AccountType> getAll() {
        return accountTypeRepository.findAll();
    }
}
