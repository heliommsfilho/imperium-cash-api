package com.github.heliommsfilho.imperium_cash.api.business.service.systemspace.banklogo;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BankLogo;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.banklogo.BankLogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankLogoService {

    private final BankLogoRepository bankLogoRepository;

    @Autowired
    public BankLogoService(BankLogoRepository bankLogoRepository) {
        this.bankLogoRepository = bankLogoRepository;
    }

    public List<BankLogo> getAll() {
        return bankLogoRepository.findAll();
    }
}
