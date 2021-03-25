package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Category;
import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.CategoryInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Category Service should")
@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {

    @Mock
    private CategoryRepository repository;
    private CategoryService service;

    @BeforeEach
    public void init() {
        this.service = new CategoryService(repository);
    }

    @Test
    @DisplayName("create a new Category")
    void createNewCategory() {
        when(repository.save(any())).thenReturn(getMockResultCreateDTO());
        Category category = service.create(getMockCreateDTO());

        Assertions.assertEquals(1L, category.getId());
        Assertions.assertEquals(2L, category.getGroupCategory().getId());
        Assertions.assertEquals("Category Name", category.getName());
    }

    private CategoryInput getMockCreateDTO() {
        return GenericBuilder.build(CategoryInput.class)
                .with(c -> c.setGroupCategoryId(2L))
                .with(c -> c.setName("Category Name"))
                .get();
    }

    private Category getMockResultCreateDTO() {
        return GenericBuilder.build(Category.class)
                .with(c -> c.setId(1L))
                .with(c -> c.setName("Category Name"))
                .with(c -> c.setGroupCategory(new GroupCategory()))
                .with(c -> c.getGroupCategory().setId(2L))
                .with(c -> c.setCreationDate(LocalDateTime.now()))
                .with(c -> c.setLastUpdate(LocalDateTime.now()))
                .with(c -> c.setDisabled(false))
                .get();
    }
}
