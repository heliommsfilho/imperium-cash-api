package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.userspace.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
