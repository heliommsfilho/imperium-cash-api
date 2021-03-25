package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.AccountType;
import com.github.heliommsfilho.imperium_cash.api.domain.model.BankLogo;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.AccountInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.AccountRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Account Service should")
@ExtendWith(MockitoExtension.class)
class AccountServiceTests {

    @Mock
    private AccountRepository repository;
    private AccountService service;

    @BeforeEach
    public void init() {
        this.service = new AccountService(repository);
    }

    @Test
    @DisplayName("create a new Account")
    void createNewAccount() {
        when(repository.save(any())).thenReturn(getMockResultCreateDTO());
        Account account = service.create(getMockCreateDTO());

        Assertions.assertEquals(1L, account.getId());
        Assertions.assertEquals(2L, account.getBudget().getId());
        Assertions.assertEquals(3L, account.getAccountType().getId());
        Assertions.assertEquals(4L, account.getBankLogo().getId());
        Assertions.assertEquals("Account Name", account.getName());
    }

    private AccountInput getMockCreateDTO() {
        return GenericBuilder.build(AccountInput.class)
                             .with(a -> a.setBudgetId(1L))
                             .with(a -> a.setAccountTypeId(2L))
                             .with(a -> a.setBankLogoId(3L))
                             .with(a -> a.setName("Account Name"))
                             .get();
    }

    private Account getMockResultCreateDTO() {
        return GenericBuilder.build(Account.class)
                             .with(a -> a.setId(1L))
                             .with(a -> a.setName("Account Name"))
                             .with(a -> a.setBudget(new Budget()))
                             .with(a -> a.getBudget().setId(2L))
                             .with(a -> a.setAccountType(new AccountType()))
                             .with(a -> a.getAccountType().setId(3L))
                             .with(a -> a.setBankLogo(new BankLogo()))
                             .with(a -> a.getBankLogo().setId(4L))
                             .with(a -> a.setCreationDate(LocalDateTime.now()))
                             .with(a -> a.setLastUpdate(LocalDateTime.now()))
                             .with(a -> a.setDisabled(false))
                             .get();
    }
}
