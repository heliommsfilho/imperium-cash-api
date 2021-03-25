package com.github.heliommsfilho.imperium_cash.api.resource.system;

import com.github.heliommsfilho.imperium_cash.api.resource.AbstractTestResource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Tag("Integration Tests")
@DisplayName("Currency Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CurrencyResourceTests extends AbstractTestResource {

    @Autowired
    private CurrencyResource currencyResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(currencyResource);
    }

    @Test
    @DisplayName("return JSON list of Currencies")
    void returnJsonListCurrencies() throws Exception {
        okMockMvc.perform(get("/currency")).andExpect(jsonPath("$", Matchers.hasSize(3)));
    }
}
