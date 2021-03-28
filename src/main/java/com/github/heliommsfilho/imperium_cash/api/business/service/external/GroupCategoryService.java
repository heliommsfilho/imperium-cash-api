package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.GroupCategoryRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupCategoryService {

    private final GroupCategoryRepository groupCategoryRepository;
    private final BudgetService budgetService;

    @Autowired
    public GroupCategoryService(GroupCategoryRepository groupCategoryRepository, BudgetService budgetService) {
        this.groupCategoryRepository = groupCategoryRepository;
        this.budgetService = budgetService;
    }

    public GroupCategory create(GroupCategory groupCategory) {
        validate(groupCategory);

        groupCategory.setId(null);
        groupCategory.setDisabled(false);

        return getGroupCategoryRepository().save(groupCategory);
    }

    public Optional<GroupCategory> getGroupCategory(Long id) {
        return getGroupCategoryRepository().findById(id);
    }

    void validate(GroupCategory groupCategoryInput) {
        Optional<Budget> budgetFound = getBudgetService().getBudget(groupCategoryInput.getBudget().getId());
        budgetFound.orElseThrow(() -> new EntityNotRegisteredException("Budget", groupCategoryInput.getBudget().getId().toString()));

        Optional<Long> groupCategoryFound = getGroupCategoryRepository().findByName(groupCategoryInput.getName());
        groupCategoryFound.ifPresent(g -> { throw new EntityAlreadyRegisteredException("Group Category", groupCategoryInput.getName()); });
    }

    public GroupCategoryRepository getGroupCategoryRepository() {
        return groupCategoryRepository;
    }

    public BudgetService getBudgetService() {
        return budgetService;
    }
}
