package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.GroupCategoryInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.GroupCategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupCategoryService {

    private final GroupCategoryRepository repository;

    @Autowired
    public GroupCategoryService(GroupCategoryRepository repository) {
        this.repository = repository;
    }

    public GroupCategory create(GroupCategoryInput createDTO) {
        GroupCategory groupCategory = EntityDTOHelper.getInstance().map(createDTO, GroupCategory.class);
        groupCategory.setId(null);
        groupCategory.setDisabled(false);

        return repository.save(groupCategory);
    }
}
