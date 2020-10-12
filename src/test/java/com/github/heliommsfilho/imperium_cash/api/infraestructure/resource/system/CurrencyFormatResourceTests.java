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
@DisplayName("Currency Format Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CurrencyFormatResourceTests extends AbstractTestResource {

    @Autowired
    private CurrencyFormatResource currencyFormatResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(currencyFormatResource);
    }

    @Test
    @DisplayName("return JSON list of Currency Formats")
    void returnJsonListCurrencyFormats() throws Exception {
        okMockMvc.perform(get("/currency_format")).andExpect(jsonPath("$", Matchers.hasSize(4)));
    }
}
