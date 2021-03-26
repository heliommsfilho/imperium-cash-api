package com.github.heliommsfilho.imperium_cash.api.domain.repository.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>, BudgetRepositoryQuery {
}
