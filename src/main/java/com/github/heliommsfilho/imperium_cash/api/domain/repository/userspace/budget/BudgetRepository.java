package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>, BudgetRepositoryQuery {

    @Transactional
    @Modifying
    @Query("UPDATE Budget SET active = :active WHERE id = :id")
    void updateStatus(@Param("id") Long id, @Param("active") boolean active);
}
