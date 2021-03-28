package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.GroupCategoryInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.GroupCategoryRepository;
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
@DisplayName("Group Category Service should")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GroupCategoryServiceTests {

    @Mock
    private GroupCategoryRepository groupCategoryRepository;

    @Mock
    private BudgetService budgetService;

    @Mock
    private GroupCategoryService groupCategoryService;

    @Test
    @DisplayName("create a new Group Category")
    void createNewGroupCategory() {
        doNothing().when(groupCategoryService).validate(any());
        when(groupCategoryRepository.save(any())).thenReturn(getMockResoultInput());
        when(groupCategoryService.getGroupCategoryRepository()).thenReturn(this.groupCategoryRepository);
        when(groupCategoryService.create(any())).thenCallRealMethod();

        GroupCategory groupCategory = MapperHelper.getInstance().map(getInput(), GroupCategory.class);
        GroupCategory savedGroupCategory = groupCategoryService.create(groupCategory);

        Assertions.assertEquals(1L, savedGroupCategory.getId());
        Assertions.assertEquals(2L, savedGroupCategory.getBudget().getId());
        Assertions.assertEquals("Group Category Name", savedGroupCategory.getName());
    }

    @Test
    @DisplayName("fail due non existent Budget")
    void createNewGroupCategory_withNonExistentBudget_shouldFail() {
        when(budgetService.getBudget(any())).thenReturn(Optional.empty());
        when(groupCategoryService.getBudgetService()).thenReturn(this.budgetService);
        when(groupCategoryService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(groupCategoryService).validate(any());

        GroupCategory groupCategory = MapperHelper.getInstance().map(getInput(), GroupCategory.class);
        Exception exception = Assertions.assertThrows(EntityNotRegisteredException.class,
                                                      () -> groupCategoryService.create(groupCategory));

        Assertions.assertEquals("Entity 'Budget' not registered with identifier '2'.", exception.getMessage());
    }

    @Test
    @DisplayName("fail due already registered name")
    void createNewGroupCategory_withAlreadyRegisteredName_shouldFail() {
        Budget mockBudgetFound = GenericBuilder.build(Budget.class).with(b -> b.setId(1L)).get();
        when(budgetService.getBudget(any())).thenReturn(Optional.of(mockBudgetFound));
        when(groupCategoryRepository.findByName(any())).thenReturn(Optional.of(1L));
        when(groupCategoryService.getBudgetService()).thenReturn(this.budgetService);
        when(groupCategoryService.getGroupCategoryRepository()).thenReturn(this.groupCategoryRepository);
        when(groupCategoryService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(groupCategoryService).validate(any());

        GroupCategory groupCategory = MapperHelper.getInstance().map(getInput(), GroupCategory.class);
        Exception exception = Assertions.assertThrows(EntityAlreadyRegisteredException.class,
                                                      () -> groupCategoryService.create(groupCategory));

        Assertions.assertEquals("Entity 'Group Category' already registered with identifier 'Group Category Name'.", exception.getMessage());
    }

    private GroupCategoryInput getInput() {
        return GenericBuilder.build(GroupCategoryInput.class)
                .with(g -> g.setBudgetId(2L))
                .with(g -> g.setName("Group Category Name"))
                .get();
    }

    private GroupCategory getMockResoultInput() {
        return GenericBuilder.build(GroupCategory.class)
                .with(g -> g.setId(1L))
                .with(g -> g.setName("Group Category Name"))
                .with(g -> g.setBudget(new Budget()))
                .with(g -> g.getBudget().setId(2L))
                .with(g -> g.setCreationDate(LocalDateTime.now()))
                .with(g -> g.setLastUpdate(LocalDateTime.now()))
                .with(g -> g.setDisabled(false))
                .get();
    }
}
