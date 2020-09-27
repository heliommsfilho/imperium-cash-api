package com.github.heliommsfilho.imperium_cash.api.business.service.systemspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.BankLogo;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.banklogo.BankLogoRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankLogoServiceTests {

    @Mock
    private BankLogoRepository bankLogoRepository;

    @Test
    void bankLogoGetAll_shouldReturnBankLogoList() {
        BankLogo bankLogo1 = GenericBuilder.build(BankLogo.class)
                                           .with(b -> b.setBankName("Banco do Brasil"))
                                           .with(b -> b.setImageData("Base64"))
                                           .get();

        BankLogo bankLogo2 = GenericBuilder.build(BankLogo.class)
                                           .with(b -> b.setBankName("Millennium BCP"))
                                           .with(b -> b.setImageData("Base64"))
                                           .get();

        when(bankLogoRepository.findAll()).thenReturn(Arrays.asList(bankLogo1, bankLogo2));

        List<BankLogo> bankLogoList = bankLogoRepository.findAll();

        Assertions.assertEquals(2, bankLogoList.size());
        Assertions.assertNotNull(bankLogoList.get(0).getBankName());
        Assertions.assertNotNull(bankLogoList.get(0).getImageData());
        Assertions.assertNotNull(bankLogoList.get(1).getBankName());
        Assertions.assertNotNull(bankLogoList.get(1).getImageData());
    }
}
