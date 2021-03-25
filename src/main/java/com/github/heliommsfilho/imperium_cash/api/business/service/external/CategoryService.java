package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Category;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.CategoryInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(CategoryInput createDTO) {
        Category category = EntityDTOHelper.getInstance().map(createDTO, Category.class);
        category.setId(null);
        category.setDisabled(false);

        return repository.save(category);
    }
}
