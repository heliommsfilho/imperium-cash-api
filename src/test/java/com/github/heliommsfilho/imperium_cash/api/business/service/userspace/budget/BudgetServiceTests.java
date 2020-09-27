package com.github.heliommsfilho.imperium_cash.api.business.service.userspace.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.User;
import com.github.heliommsfilho.imperium_cash.api.domain.model.userspace.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.BudgetRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BudgetServiceTests {

    @Mock
    private BudgetRepository budgetRepository;

    @Test
    void budgetGetById_shouldReturnBudget() {
        when(budgetRepository.findById(any(Long.class))).thenReturn(BudgetServiceTests.getBudget());
        Assertions.assertEquals(1L, budgetRepository.findById(1L).get().getUser().getId());
    }

    @Test
    void userGetById_shouldReturnBudget() {
        when(budgetRepository.findById(1L)).thenReturn(BudgetServiceTests.getBudget());
        Assertions.assertEquals(1L, budgetRepository.findById(1L).get().getUser().getId());
    }

    private static Optional<Budget> getBudget() {
        Budget budget = GenericBuilder.build(Budget.class)
                                      .with(b -> b.setUser(new User()))
                                      .with(b -> b.getUser().setId(1L))
                                      .with(b -> b.setName("Finances in Portugal"))
                                      .with(b -> b.setDescription("Financial control for Euro earnings"))
                                      .get();

        return Optional.ofNullable(budget);
    }
}
