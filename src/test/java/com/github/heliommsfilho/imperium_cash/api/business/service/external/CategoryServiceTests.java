package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Category;
import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.CategoryInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.helper.MapperHelper;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Category Service should")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private GroupCategoryService groupCategoryService;

    @Mock
    private CategoryService categoryService;

    @Test
    @DisplayName("create a new Category")
    void createNewCategory() {
        doNothing().when(categoryService).validate(any());
        when(categoryRepository.save(any())).thenReturn(getMockResultInput());
        when(categoryService.getCategoryRepository()).thenReturn(this.categoryRepository);
        when(categoryService.create(any())).thenCallRealMethod();

        Category categoryInput = MapperHelper.getInstance().map(getInput(), Category.class);
        Category categoryOutput = categoryService.create(categoryInput);

        Assertions.assertEquals(1L, categoryOutput.getId());
        Assertions.assertEquals(2L, categoryOutput.getGroupCategory().getId());
        Assertions.assertEquals("Category Name", categoryOutput.getName());
    }

    @Test
    @DisplayName("fail due non registered Group Category")
    void createNewCategory_withNonRegisteredGroupCategory__shouldFail() {
        when(groupCategoryService.getGroupCategory(any())).thenReturn(Optional.empty());
        when(categoryService.getGroupCategoryService()).thenReturn(this.groupCategoryService);
        when(categoryService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(categoryService).validate(any());

        Category categoryInput = MapperHelper.getInstance().map(getInput(), Category.class);
        Exception exception = Assertions.assertThrows(EntityNotRegisteredException.class,
                                                      () -> categoryService.create(categoryInput));

        Assertions.assertEquals("Entity 'Group Category' not registered with identifier '2'.", exception.getMessage());
    }

    @Test
    @DisplayName("fail due already registered Category name")
    void createNewCategory_withAlreadyRegisteredName__shouldFail() {
        GroupCategory groupCategory = GenericBuilder.build(GroupCategory.class).with(g -> g.setId(3L)).get();
        when(groupCategoryService.getGroupCategory(any())).thenReturn(Optional.of(groupCategory));
        when(categoryService.getGroupCategoryService()).thenReturn(this.groupCategoryService);
        when(categoryRepository.findByName(any())).thenReturn(Optional.of(2L));
        when(categoryService.getCategoryRepository()).thenReturn(this.categoryRepository);
        when(categoryService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(categoryService).validate(any());

        Category categoryInput = MapperHelper.getInstance().map(getInput(), Category.class);
        Exception exception = Assertions.assertThrows(EntityAlreadyRegisteredException.class,
                                                      () -> categoryService.create(categoryInput));

        Assertions.assertEquals("Entity 'Category' already registered with identifier 'Category Name'.", exception.getMessage());
    }

    private CategoryInput getInput() {
        return GenericBuilder.build(CategoryInput.class)
                .with(c -> c.setGroupCategoryId(2L))
                .with(c -> c.setName("Category Name"))
                .get();
    }

    private Category getMockResultInput() {
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
