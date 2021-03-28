package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.api.input.AccountInput;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.model.AccountType;
import com.github.heliommsfilho.imperium_cash.api.domain.model.BankLogo;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.AccountRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import com.github.heliommsfilho.imperium_cash.api.helper.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Account Service should")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AccountServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BudgetService budgetService;

    @Mock
    private AccountService accountService;

    @Test
    @DisplayName("create a new Account")
    void createNewAccount() {
        when(accountRepository.save(any())).thenReturn(getMockResultInput());
        /* Bypass Budget validation to mock a valid Budget */
        doNothing().when(accountService).validate(any());
        when(accountService.getAccountRepository()).thenReturn(this.accountRepository);
        when(accountService.create(any())).thenCallRealMethod();

        Account accountInput = MapperHelper.getInstance().map(getInput(), Account.class);
        Account newAccount = accountService.create(accountInput);

        Assertions.assertEquals(1L, newAccount.getId());
        Assertions.assertEquals(2L, newAccount.getBudget().getId());
        Assertions.assertEquals(3L, newAccount.getAccountType().getId());
        Assertions.assertEquals(4L, newAccount.getBankLogo().getId());
        Assertions.assertEquals("Account Name", newAccount.getName());
    }

    @Test
    @DisplayName("fail due non existent Budget")
    void createNewAccount_withNonRegisteredBudget__shouldFail() {
        /* Mock a non existent Budget for given ID being returned from Database */
        when(budgetService.getBudget(any())).thenReturn(Optional.empty());
        when(accountService.getBudgetService()).thenReturn(this.budgetService);
        when(accountService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(accountService).validate(any());

        Account accountInput = MapperHelper.getInstance().map(getInput(), Account.class);
        Exception exception = Assertions.assertThrows(EntityNotRegisteredException.class,
                                                      () -> accountService.create(accountInput));

        Assertions.assertEquals("Entity 'Budget' not registered with identifier '2'.", exception.getMessage());
    }

    @Test
    @DisplayName("fail due already registered Account name")
    void createNewAccount_withAlreadyRegisteredName__shouldFail() {
        Budget mockFoundBudget = GenericBuilder.build(Budget.class).with(b -> b.setId(2L)).get();
        when(budgetService.getBudget(any())).thenReturn(Optional.of(mockFoundBudget));
        when(accountService.getBudgetService()).thenReturn(this.budgetService);
        when(accountRepository.findByName(any())).thenReturn(Optional.of(1L));
        when(accountService.getAccountRepository()).thenReturn(this.accountRepository);
        when(accountService.create(any())).thenCallRealMethod();

        doCallRealMethod().when(accountService).validate(any());
        Account accountInput = MapperHelper.getInstance().map(getInput(), Account.class);
        Exception exception = Assertions.assertThrows(EntityAlreadyRegisteredException.class,
                                                      () -> accountService.create(accountInput));

        Assertions.assertEquals("Entity 'Account' already registered with identifier 'Account Name'.", exception.getMessage());
    }

    private AccountInput getInput() {
        return GenericBuilder.build(AccountInput.class)
                             .with(a -> a.setBudgetId(2L))
                             .with(a -> a.setAccountTypeId(2L))
                             .with(a -> a.setBankLogoId(3L))
                             .with(a -> a.setName("Account Name"))
                             .get();
    }

    private Account getMockResultInput() {
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
