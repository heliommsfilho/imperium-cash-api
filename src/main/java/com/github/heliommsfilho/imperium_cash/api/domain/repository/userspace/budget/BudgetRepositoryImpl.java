package com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Budget_;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.budget.BudgetGeneralResponseDTO;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.EntityDTOHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BudgetRepositoryImpl implements BudgetRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Budget> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Budget> criteria = cb.createQuery(Budget.class);
        criteria.from(Budget.class);

        return doQuery(criteria, null, BudgetFetchMode.NO_FETCH);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> getBudget(Long id) {
        return getBudget(id, BudgetFetchMode.NO_FETCH);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> getBudget(Long id, BudgetFetchMode... fetchModes) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Budget> criteria = cb.createQuery(Budget.class);
        Root<Budget> root = criteria.from(Budget.class);

        List<Predicate> where = new ArrayList<>();
        where.add(cb.equal(root.get(Budget_.ID), id));

        return EntityDTOHelper.getSingleResult(doQuery(criteria, where, fetchModes));
    }

    private List<Budget> doQuery(CriteriaQuery<Budget> criteria, List<Predicate> where, BudgetFetchMode... fetchModes) {
        Predicate[] predicates = new Predicate[0];

        if (where != null) {
            predicates = where.toArray(new Predicate[0]);
        }

        criteria.where(predicates);

        TypedQuery<Budget> typedQuery = entityManager.createQuery(criteria);
        performFetch(typedQuery.getResultList(), predicates, fetchModes);

        return typedQuery.getResultList();
    }

    private void performFetch(List<Budget> budgetList, Predicate[] predicates, BudgetFetchMode[] fetchModes) {
        boolean hasNoFetchMode = Arrays.asList(fetchModes).contains(BudgetFetchMode.NO_FETCH);
        boolean hasAnyOtherFetchMode = Arrays.stream(fetchModes).anyMatch(f -> !f.equals(BudgetFetchMode.NO_FETCH));

        if (hasNoFetchMode && hasAnyOtherFetchMode) {
            throw new IllegalArgumentException("Cannot have NO_FETCH combined with any other 'non NO_FETCH' mode");
        }

        if (hasAnyOtherFetchMode) {
            budgetList.forEach(budget -> {
                Arrays.stream(fetchModes).filter(f -> f.equals(BudgetFetchMode.FETCH_PAYEE)).findAny().ifPresent(f -> budget.setPayees(fetchPayees(predicates)));
                Arrays.stream(fetchModes).filter(f -> f.equals(BudgetFetchMode.FETCH_GROUP_CATEGORY)).findAny().ifPresent(f -> budget.setGroupCategories(fetchGroupCategories(predicates)));
            });
        }
    }

    private List<Payee> fetchPayees(Predicate[] predicates) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Budget> query = cb.createQuery(Budget.class);
        Root<Budget> root = query.from(Budget.class);
        query.where(predicates);

        root.fetch(Budget_.PAYEES, JoinType.LEFT);
        TypedQuery<Budget> resultQuery = entityManager.createQuery(query);

        List<Payee> payees;
        try {
            payees = resultQuery.getSingleResult().getPayees();
        } catch (NoResultException e) {
            payees = new ArrayList<>();
        }

        return payees;
    }

    private List<GroupCategory> fetchGroupCategories(Predicate[] predicates) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Budget> query = cb.createQuery(Budget.class);
        Root<Budget> root = query.from(Budget.class);
        query.where(predicates);

        root.fetch(Budget_.GROUP_CATEGORIES, JoinType.LEFT);
        TypedQuery<Budget> resultQuery = entityManager.createQuery(query);

        List<GroupCategory> groupCategories;
        try {
            groupCategories = resultQuery.getSingleResult().getGroupCategories();
        } catch (NoResultException e) {
            groupCategories = new ArrayList<>();
        }

        return groupCategories;
    }
}
