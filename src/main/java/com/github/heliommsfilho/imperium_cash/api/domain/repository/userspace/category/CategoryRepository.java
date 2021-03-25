package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.category;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
