package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.payee;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

    @Query("SELECT p FROM Payee p WHERE p.budget = :budgetId")
    List<Payee> getByBudget(Long budgetId);
}
