package com.github.heliommsfilho.imperium_cash.api.business.service.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Category;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.category.CategoryCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.groupcategory.GroupCategoryCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.category.CategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.groupcategory.GroupCategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(CategoryCreateDTO createDTO) {
        Category category = EntityDTOHelper.getInstance().map(createDTO, Category.class);
        category.setId(null);
        category.setDisabled(false);

        return repository.save(category);
    }
}
