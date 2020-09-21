package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace;

import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankLogoResourceTests extends AbstractTestResource {

    @Autowired
    private BankLogoResource bankLogoResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(bankLogoResource);
    }

    @Test
    void bankLogoGetAll_shouldReturnBankLogoList() throws Exception {
        okMockMvc.perform(get("/bank_logo")).andExpect(jsonPath("$", Matchers.hasSize(19)));
    }
}
