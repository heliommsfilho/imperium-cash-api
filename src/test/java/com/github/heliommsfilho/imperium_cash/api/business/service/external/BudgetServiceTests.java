package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Currency;
import com.github.heliommsfilho.imperium_cash.api.domain.model.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.DateFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.User;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.BudgetInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.budget.BudgetRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Budget Service should")
@ExtendWith(MockitoExtension.class)
class BudgetServiceTests {

    @Mock
    private BudgetRepository budgetRepository;
    private BudgetServicie budgetService;

    @BeforeEach
    public void init() {
        budgetService = new BudgetServicie(budgetRepository);
    }

    @Test
    @DisplayName("return a Budget by ID")
    void returnBudgetById() {
        when(budgetRepository.getBudget(any(Long.class))).thenReturn(getResultBudget());
        Optional<Budget> budget = budgetService.getBudget(1L);

        Assertions.assertTrue(budget.isPresent());
        Assertions.assertEquals("63796157-4646-4627-a40f-9718e55d9999", budget.get().getUuid());
        Assertions.assertEquals(1L, budget.get().getUser().getId());
        Assertions.assertEquals("Finances in Portugal", budget.get().getName());
        Assertions.assertEquals("Financial control for Euro earnings", budget.get().getDescription());
        Assertions.assertEquals(1L, budget.get().getCurrency().getId());
        Assertions.assertEquals(1L, budget.get().getCurrencyFormat().getId());
        Assertions.assertEquals(1L, budget.get().getDateFormat().getId());
        Assertions.assertTrue(budget.get().getActive());
        Assertions.assertNotNull(budget.get().getCreationDate());
        Assertions.assertNotNull(budget.get().getLastUpdate());
    }

    @Test
    @DisplayName("create a new Budget")
    void createNewBudget() {
        when(budgetRepository.save(any())).thenReturn(getResultCreateBudget());
        Budget savedBudget = budgetService.create(getCreateDTO());

        Assertions.assertNotNull(savedBudget.getUuid());
        Assertions.assertEquals(1, savedBudget.getUser().getId());
        Assertions.assertEquals("Name", savedBudget.getName());
        Assertions.assertEquals("Description", savedBudget.getDescription());
        Assertions.assertEquals(1, savedBudget.getCurrency().getId());
        Assertions.assertEquals(1, savedBudget.getCurrencyFormat().getId());
        Assertions.assertEquals(1, savedBudget.getDateFormat().getId());
        Assertions.assertNotNull(savedBudget.getCreationDate());
        Assertions.assertNotNull(savedBudget.getLastUpdate());
        Assertions.assertEquals(savedBudget.getCreationDate(), savedBudget.getLastUpdate());
    }

    @Test
    public void inactivate() {
        budgetService.inactivate(1L);
    }

    @Test
    public void activate() {
        budgetService.activate(1L);
    }

    private static Optional<Budget> getResultBudget() {
        Budget budget = GenericBuilder.build(Budget.class)
                                      .with(b -> b.setUuid("63796157-4646-4627-a40f-9718e55d9999"))
                                      .with(b -> b.setUser(new User()))
                                      .with(b -> b.getUser().setId(1L))
                                      .with(b -> b.setName("Finances in Portugal"))
                                      .with(b -> b.setDescription("Financial control for Euro earnings"))
                                      .with(b -> b.setCurrency(new Currency()))
                                      .with(b -> b.getCurrency().setId(1L))
                                      .with(b -> b.setCurrencyFormat(new CurrencyFormat()))
                                      .with(b -> b.getCurrencyFormat().setId(1L))
                                      .with(b -> b.setDateFormat(new DateFormat()))
                                      .with(b -> b.getDateFormat().setId(1L))
                                      .with(b -> b.setActive(true))
                                      .with(b -> b.setCreationDate(LocalDateTime.now()))
                                      .with(b -> b.setLastUpdate(LocalDateTime.now()))
                                      .get();

        return Optional.ofNullable(budget);
    }

    private static Budget getResultCreateBudget() {
        return GenericBuilder.build(Budget.class)
                             .with(b -> b.setUuid(UUID.randomUUID().toString()))
                             .with(b -> b.setUser(new User()))
                             .with(b -> b.getUser().setId(1L))
                             .with(b -> b.setName("Name"))
                             .with(b -> b.setDescription("Description"))
                             .with(b -> b.setCurrency(new Currency()))
                             .with(b -> b.getCurrency().setId(1L))
                             .with(b -> b.setCurrencyFormat(new CurrencyFormat()))
                             .with(b -> b.getCurrencyFormat().setId(1L))
                             .with(b -> b.setDateFormat(new DateFormat()))
                             .with(b -> b.getDateFormat().setId(1L))
                             .with(b -> b.setActive(true))
                             .with(b -> b.setCreationDate(LocalDateTime.now()))
                             .with(b -> b.setLastUpdate(b.getCreationDate()))
                             .get();
    }

    private static BudgetInput getCreateDTO() {
        return GenericBuilder.build(BudgetInput.class)
                             .with(b -> b.setUuid("11111111-4646-4627-a40f-9718e55d0000"))
                             .with(b -> b.setName("Name"))
                             .with(b -> b.setDescription("Description"))
                             .with(b -> b.setUserId(1L))
                             .with(b -> b.setCurrencyId(1L))
                             .with(b -> b.setCurrencyFormatId(1L))
                             .with(b -> b.setDateFormatId(1L))
                             .get();
    }
}
