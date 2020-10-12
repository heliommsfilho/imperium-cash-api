package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.system;

import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Tag("Integration Tests")
@DisplayName("Account Type Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTypeResourceTests extends AbstractTestResource {

    @Autowired
    private AccountTypeResource accountTypeResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(accountTypeResource);
    }

    @Test
    @DisplayName("return JSON list of Account Types")
    void returnJsonListAccountTypes() throws Exception {
        okMockMvc.perform(get("/account_type")).andExpect(jsonPath("$", hasSize(4)));
    }
}
