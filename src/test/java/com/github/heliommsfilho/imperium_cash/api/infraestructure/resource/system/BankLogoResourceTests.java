package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.system;

import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Tag("Integration Tests")
@DisplayName("Bank Logo Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankLogoResourceTests extends AbstractTestResource {

    @Autowired
    private BankLogoResource bankLogoResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(bankLogoResource);
    }

    @Test
    @DisplayName("return JSON list of Bank Logos")
    void returnJsonListBankLogos() throws Exception {
        okMockMvc.perform(get("/bank_logo")).andExpect(jsonPath("$", Matchers.hasSize(19)));
    }
}
