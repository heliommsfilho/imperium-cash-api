package com.github.heliommsfilho.imperium_cash.api.business.service.user.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.budget.BudgetCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.budget.BudgetFetchMode;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.budget.BudgetRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return budgetRepository.getBudget(id, BudgetFetchMode.FETCH_PAYEE, BudgetFetchMode.FETCH_GROUP_CATEGORY);
    }

    public List<Budget> getAll() {
        return budgetRepository.getAll();
    }

    public Budget create(BudgetCreateDTO createDTO) {
        Budget budget = EntityDTOHelper.getInstance().map(createDTO, Budget.class);
        budget.setUuid(UUID.randomUUID().toString());
        LocalDateTime now = LocalDateTime.now();
        budget.setCreationDate(now);
        budget.setLastUpdate(now);

        Budget budgetSaved = budgetRepository.save(budget);
        budget.setId(budgetSaved.getId());

        return budget;
    }

    public void activate(Long id) {
        budgetRepository.updateStatus(id, true);
    }

    public void inactivate(Long id) {
        budgetRepository.updateStatus(id, false);
    }
}
