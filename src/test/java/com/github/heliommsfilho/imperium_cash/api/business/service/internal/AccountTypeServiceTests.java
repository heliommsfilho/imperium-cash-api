package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.AccountType;
import com.github.heliommsfilho.imperium_cash.api.domain.model.AccountTypeEnum;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.AccountTypeRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Account Type Service should")
@ExtendWith(MockitoExtension.class)
class AccountTypeServiceTests {

    @Mock
    private AccountTypeRepository accountTypeRepository;
    private AccountTypeService accountTypeService;

    @BeforeEach
    public void init() {
        accountTypeService = new AccountTypeService(accountTypeRepository);
    }

    @Test
    @DisplayName("return all Account Types")
    void returnAllAccountTypes() {
        when(accountTypeRepository.findAll()).thenReturn(AccountTypeServiceTests.getAccountTypes());
        Assertions.assertEquals(4, accountTypeService.getAll().size());
    }

    private static List<AccountType> getAccountTypes() {
        List<AccountType> accountTypes = new ArrayList<>();
        accountTypes.add(GenericBuilder.build(AccountType.class)
                                       .with(a -> a.setAccountTypeEnum(AccountTypeEnum.CHECKING))
                                       .with(a -> a.setName("Checking Account"))
                                       .get());

        accountTypes.add(GenericBuilder.build(AccountType.class)
                                       .with(a -> a.setAccountTypeEnum(AccountTypeEnum.WALLET))
                                       .with(a -> a.setName("Wallet"))
                                       .get());

        accountTypes.add(GenericBuilder.build(AccountType.class)
                                       .with(a -> a.setAccountTypeEnum(AccountTypeEnum.SAVINGS))
                                       .with(a -> a.setName("Savings Account"))
                                       .get());

        accountTypes.add(GenericBuilder.build(AccountType.class)
                                       .with(a -> a.setAccountTypeEnum(AccountTypeEnum.INVESTIMENT))
                                       .with(a -> a.setName("Investment Account"))
                                       .get());

        return accountTypes;
    }
}
