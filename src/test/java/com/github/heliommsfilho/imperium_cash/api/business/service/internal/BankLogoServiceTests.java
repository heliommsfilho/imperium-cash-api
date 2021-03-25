package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BankLogo;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.BankLogoRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Bank Logo Service should")
@ExtendWith(MockitoExtension.class)
class BankLogoServiceTests {

    @Mock
    private BankLogoRepository bankLogoRepository;
    private BankLogoService bankLogoService;

    @BeforeEach
    public void init() {
        bankLogoService = new BankLogoService(bankLogoRepository);
    }

    @Test
    @DisplayName("return all Bank Logos")
    void returnAllBankLogos() {
        when(bankLogoRepository.findAll()).thenReturn(createBankLogos());

        List<BankLogo> bankLogos = bankLogoService.getAll();
        Assertions.assertEquals(2, bankLogos.size());
        Assertions.assertNotNull(bankLogos.get(0).getBankName());
        Assertions.assertNotNull(bankLogos.get(0).getImageData());
        Assertions.assertNotNull(bankLogos.get(1).getBankName());
        Assertions.assertNotNull(bankLogos.get(1).getImageData());
    }

    private static List<BankLogo> createBankLogos() {
        List<BankLogo> bankLogos = new ArrayList<>();
        bankLogos.add(GenericBuilder.build(BankLogo.class)
                                    .with(b -> b.setBankName("Banco do Brasil"))
                                    .with(b -> b.setImageData("Base64"))
                                    .get());

        bankLogos.add(GenericBuilder.build(BankLogo.class)
                                    .with(b -> b.setBankName("Millennium BCP"))
                                    .with(b -> b.setImageData("Base64"))
                                    .get());

        return bankLogos;
    }
}
