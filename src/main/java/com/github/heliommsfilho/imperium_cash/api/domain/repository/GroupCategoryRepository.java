package com.github.heliommsfilho.imperium_cash.api.domain.repository;

import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCategoryRepository extends JpaRepository<GroupCategory, Long> {
}
