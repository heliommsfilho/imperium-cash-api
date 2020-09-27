package com.github.heliommsfilho.imperium_cash.api.business.service.userspace.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.userspace.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetServicie {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetServicie(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Optional<Budget> getById(Long id) {
        return budgetRepository.findById(id);
    }
}
