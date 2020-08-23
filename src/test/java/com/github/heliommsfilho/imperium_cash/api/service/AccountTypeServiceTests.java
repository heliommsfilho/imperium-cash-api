package com.github.heliommsfilho.imperium_cash.api.service;

import com.github.heliommsfilho.imperium_cash.api.model.AccountType;
import com.github.heliommsfilho.imperium_cash.api.model.AccountTypeEnum;
import com.github.heliommsfilho.imperium_cash.api.repository.accounttype.AccountTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountTypeServiceTests {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @Test
    public void accountTypeGetAll_shouldReturnAccountTypeList() {
        when(accountTypeRepository.findAll()).thenReturn(AccountTypeServiceTests.getAccountTypes());
        Assertions.assertEquals(4, accountTypeRepository.findAll().size());
    }

    @Test
    public void accountTypeGetById_shouldReturnAccountType() {
        when(accountTypeRepository.findById(any(Long.class))).thenReturn(Optional.of(AccountTypeServiceTests.getAccountTypes().get(0)))
                                                             .thenReturn(Optional.of(AccountTypeServiceTests.getAccountTypes().get(1)))
                                                             .thenReturn(Optional.of(AccountTypeServiceTests.getAccountTypes().get(2)))
                                                             .thenReturn(Optional.of(AccountTypeServiceTests.getAccountTypes().get(3)));
        Assertions.assertEquals(AccountTypeEnum.CHECKING, accountTypeRepository.findById(1L).get().getAccountType());
        Assertions.assertEquals(AccountTypeEnum.WALLET, accountTypeRepository.findById(2L).get().getAccountType());
        Assertions.assertEquals(AccountTypeEnum.SAVINGS, accountTypeRepository.findById(3L).get().getAccountType());
        Assertions.assertEquals(AccountTypeEnum.INVESTIMENT, accountTypeRepository.findById(4L).get().getAccountType());
    }

    private static List<AccountType> getAccountTypes() {
        List<AccountType> accountTypes = new ArrayList<>();

        AccountType accountTypeChecking = new AccountType();
        accountTypeChecking.setAccountType(AccountTypeEnum.CHECKING);
        accountTypeChecking.setName("Checking Account");

        AccountType accountTypeWallet = new AccountType();
        accountTypeWallet.setAccountType(AccountTypeEnum.WALLET);
        accountTypeWallet.setName("Wallet");

        AccountType accountTypeSavings = new AccountType();
        accountTypeSavings.setAccountType(AccountTypeEnum.SAVINGS);
        accountTypeSavings.setName("Savings Account");

        AccountType accountTypeInvestments = new AccountType();
        accountTypeInvestments.setAccountType(AccountTypeEnum.INVESTIMENT);
        accountTypeInvestments.setName("Investment Account");

        accountTypes.add(accountTypeChecking);
        accountTypes.add(accountTypeWallet);
        accountTypes.add(accountTypeSavings);
        accountTypes.add(accountTypeInvestments);

        return accountTypes;
    }
}
