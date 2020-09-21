package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace;

import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace.CurrencyFormatResource;
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
class CurrencyFormatResourceTests extends AbstractTestResource {

    @Autowired
    private CurrencyFormatResource currencyFormatResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(currencyFormatResource);
    }

    @Test
    void currencyFormatGetAll_shouldReturnCurrencyFormatList() throws Exception {
        okMockMvc.perform(get("/currency_format")).andExpect(jsonPath("$", Matchers.hasSize(4)));
    }
}
