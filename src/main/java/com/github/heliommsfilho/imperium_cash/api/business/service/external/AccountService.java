package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.AccountRepository;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.budget.BudgetRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BudgetService budgetService;

    @Autowired
    public AccountService(AccountRepository accountRepository, BudgetService budgetService) {
        this.accountRepository = accountRepository;
        this.budgetService = budgetService;
    }

    public Account create(Account account) {
        validate(account);

        account.setId(null);
        account.setDisabled(false);

        return getAccountRepository().save(account);
    }

    void validate(Account accountInput) {
        Optional<Budget> budgetFound = getBudgetService().getBudget(accountInput.getBudget().getId());
        budgetFound.orElseThrow(() -> new EntityNotRegisteredException("Budget", accountInput.getBudget().getId().toString()));

        Optional<Long> accountFound = getAccountRepository().findByName(accountInput.getName());
        accountFound.ifPresent(a -> { throw new EntityAlreadyRegisteredException("Account", accountInput.getName()); });
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public BudgetService getBudgetService() {
        return budgetService;
    }
}
