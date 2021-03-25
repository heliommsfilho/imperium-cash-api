package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.BudgetInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.budget.BudgetFetchMode;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.budget.BudgetRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BudgetServicie {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetServicie(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Optional<Budget> getBudget(Long id) {
        return budgetRepository.getBudget(id);
    }

    public Optional<Budget> getBudgetWithDetails(Long id) {
        return budgetRepository.getBudget(id, BudgetFetchMode.FETCH_PAYEES, BudgetFetchMode.FETCH_GROUP_CATEGORIES, BudgetFetchMode.FETCH_ACCOUNTS);
    }

    public List<Budget> getAll() {
        return budgetRepository.getAll();
    }

    public Budget create(BudgetInput createDTO) {
        Budget budget = EntityDTOHelper.getInstance().map(createDTO, Budget.class);
        budget.setUuid(UUID.randomUUID().toString());
        return budgetRepository.save(budget);
    }

    public void activate(Long id) {
        budgetRepository.updateStatus(id, true);
    }

    public void inactivate(Long id) {
        budgetRepository.updateStatus(id, false);
    }
}
