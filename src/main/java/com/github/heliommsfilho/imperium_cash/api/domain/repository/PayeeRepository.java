package com.github.heliommsfilho.imperium_cash.api.domain.repository;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

    @Query("SELECT p.id FROM Payee p WHERE LOWER(p.name) = LOWER(:name)")
    Optional<Long> findByName(@Param("name") String name);
}
