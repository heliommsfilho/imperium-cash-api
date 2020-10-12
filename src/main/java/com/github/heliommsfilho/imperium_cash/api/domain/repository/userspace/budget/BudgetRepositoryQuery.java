package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetRepositoryQuery {

    Optional<Budget> getBudget(Long id);
    Optional<Budget> getBudget(Long id, BudgetFetchMode... fetchMode);
    List<Budget> getAll();
}
