package com.github.heliommsfilho.imperium_cash.api.domain.repository;

import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupCategoryRepository extends JpaRepository<GroupCategory, Long> {

    @Query(value = "SELECT g.id FROM GroupCategory g WHERE g.id = :id AND g.disabled = FALSE")
    Optional<GroupCategory> findById(@Param("id") Long id);

    @Query(value = "SELECT g.id FROM GroupCategory g WHERE LOWER(g.name) = LOWER(:name)")
    Optional<Long> findByName(@Param("name") String name);
}
