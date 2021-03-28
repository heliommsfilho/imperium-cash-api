package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Category;
import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final GroupCategoryService groupCategoryService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, GroupCategoryService groupCategoryService) {
        this.categoryRepository = categoryRepository;
        this.groupCategoryService = groupCategoryService;
    }

    public Category create(Category category) {
        validate(category);

        category.setId(null);
        category.setDisabled(false);

        return getCategoryRepository().save(category);
    }

    void validate(Category categoryInput) {
        Long groupCategoryId = categoryInput.getGroupCategory().getId();
        Optional<GroupCategory> groupCategoryFound = getGroupCategoryService().getGroupCategory(groupCategoryId);
        groupCategoryFound.orElseThrow(() -> { throw new EntityNotRegisteredException("Group Category", groupCategoryId.toString()); });

        String categoryName = categoryInput.getName();
        Optional<Long> categoryFound = getCategoryRepository().findByName(categoryName);
        categoryFound.ifPresent(c -> { throw new EntityAlreadyRegisteredException("Category", categoryName); });
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public GroupCategoryService getGroupCategoryService() {
        return groupCategoryService;
    }
}
