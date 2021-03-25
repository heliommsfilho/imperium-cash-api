package com.github.heliommsfilho.imperium_cash.api.business.service.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.groupcategory.GroupCategoryCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee.PayeeCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.groupcategory.GroupCategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.payee.PayeeRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Group Category Service should")
@ExtendWith(MockitoExtension.class)
class GroupCategoryServiceTests {

    @Mock
    private GroupCategoryRepository repository;
    private GroupCategoryService service;

    @BeforeEach
    public void init() {
        this.service = new GroupCategoryService(repository);
    }

    @Test
    @DisplayName("create a new Group Category")
    void createNewGroupCategory() {
        when(repository.save(any())).thenReturn(getMockResoultCreateDTO());
        GroupCategory savedGroupCategory = service.create(getMockCreateDTO());

        Assertions.assertEquals(1L, savedGroupCategory.getId());
        Assertions.assertEquals(2L, savedGroupCategory.getBudget().getId());
        Assertions.assertEquals("Group Category Name", savedGroupCategory.getName());
    }

    private GroupCategoryCreateDTO getMockCreateDTO() {
        return GenericBuilder.build(GroupCategoryCreateDTO.class)
                .with(g -> g.setBudgetId(1L))
                .with(g -> g.setName("Group Category Name"))
                .get();
    }

    private GroupCategory getMockResoultCreateDTO() {
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
