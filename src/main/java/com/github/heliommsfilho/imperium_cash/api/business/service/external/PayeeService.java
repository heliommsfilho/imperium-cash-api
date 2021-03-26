package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.PayeeRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayeeService {

    private final PayeeRepository payeeRepository;
    private final BudgetServicie budgetServicie;

    @Autowired
    public PayeeService(PayeeRepository payeeRepository, BudgetServicie budgetServicie) {
        this.payeeRepository = payeeRepository;
        this.budgetServicie = budgetServicie;
    }

    public Payee create(Payee payee) {
        validate(payee);

        payee.setId(null);
        payee.setDisabled(false);

        return getPayeeRepository().save(payee);
    }

    void validate(Payee payeeInput) {
        Optional<Budget> budgetFound = getBudgetServicie().getBudget(payeeInput.getBudget().getId());
        budgetFound.orElseThrow(() -> new EntityNotRegisteredException("Payee", payeeInput.getBudget().getId().toString()));

        Optional<Payee> payeeFound = getPayeeRepository().getByName(payeeInput.getName());
        payeeFound.ifPresent(p -> { throw new EntityAlreadyRegisteredException("Payee", payeeInput.getName()); });
    }

    PayeeRepository getPayeeRepository() {
        return payeeRepository;
    }

    public BudgetServicie getBudgetServicie() {
        return budgetServicie;
    }
}
