package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.PayeeInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.PayeeRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayeeService {

    private final PayeeRepository repository;

    @Autowired
    public PayeeService(PayeeRepository repository) {
        this.repository = repository;
    }

    public Payee create(PayeeInput createDTO) {
        Payee payee = EntityDTOHelper.getInstance().map(createDTO, Payee.class);
        payee.setId(null);
        payee.setDisabled(false);

        return repository.save(payee);
    }
}
