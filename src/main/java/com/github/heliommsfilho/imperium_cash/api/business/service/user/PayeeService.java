package com.github.heliommsfilho.imperium_cash.api.business.service.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee.PayeeCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee.PayeeGeneralResponseDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.userspace.payee.PayeeRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayeeService {

    private final PayeeRepository repository;

    @Autowired
    public PayeeService(PayeeRepository repository) {
        this.repository = repository;
    }

    public Payee create(PayeeCreateDTO createDTO) {
        Payee payee = EntityDTOHelper.getInstance().map(createDTO, Payee.class);
        payee.setId(null);
        payee.setDisabled(false);

        return repository.save(payee);
    }
}
