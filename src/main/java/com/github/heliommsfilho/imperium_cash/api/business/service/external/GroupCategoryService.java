package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.GroupCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupCategoryService {

    private final GroupCategoryRepository repository;

    @Autowired
    public GroupCategoryService(GroupCategoryRepository repository) {
        this.repository = repository;
    }

    public GroupCategory create(GroupCategory groupCategory) {
        groupCategory.setId(null);
        groupCategory.setDisabled(false);

        return repository.save(groupCategory);
    }
}
