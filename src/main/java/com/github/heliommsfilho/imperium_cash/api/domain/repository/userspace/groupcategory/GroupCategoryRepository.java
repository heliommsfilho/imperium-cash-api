package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.groupcategory;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCategoryRepository extends JpaRepository<GroupCategory, Long> {
}
