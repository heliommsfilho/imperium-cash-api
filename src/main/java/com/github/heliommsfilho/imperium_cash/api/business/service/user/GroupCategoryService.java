package com.github.heliommsfilho.imperium_cash.api.business.service.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.groupcategory.GroupCategoryCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.groupcategory.GroupCategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupCategoryService {

    private final GroupCategoryRepository repository;

    @Autowired
    public GroupCategoryService(GroupCategoryRepository repository) {
        this.repository = repository;
    }

    public GroupCategory create(GroupCategoryCreateDTO createDTO) {
        GroupCategory groupCategory = EntityDTOHelper.getInstance().map(createDTO, GroupCategory.class);
        groupCategory.setId(null);
        groupCategory.setDisabled(false);

        return repository.save(groupCategory);
    }
}
