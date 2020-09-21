package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace;

import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace.AccountTypeResource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTypeResourceTests extends AbstractTestResource {

    @Autowired
    private AccountTypeResource accountTypeResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(accountTypeResource);
    }

    @Test
    void findAllAccountTypes_shouldReturnOk() throws Exception {
        okMockMvc.perform(get("/account_type"))
                .andExpect(jsonPath("$", hasSize(4)));
    }
}
