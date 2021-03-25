package com.github.heliommsfilho.imperium_cash.api.resource.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.AccountInput;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import com.github.heliommsfilho.imperium_cash.api.resource.AbstractTestResource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Tag("Integration Tests")
@DisplayName("Account Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountResourceTests extends AbstractTestResource {

    @Autowired
    private AccountResource accountResource;

    private MockMvc createdMockMvc;

    @BeforeAll
    void setup() {
        createdMockMvc = getCreatedMockMvc(accountResource);
    }

    @Test
    @DisplayName("create a new Account")
    @Transactional
    void createNewAccount() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(getMockRequest());

        createdMockMvc.perform(post("/account")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                      .andExpect(jsonPath("$.name", Matchers.is("Montepio")))
                      .andExpect(jsonPath("$.budgetId", Matchers.is(1)))
                      .andExpect(jsonPath("$.accountTypeId", Matchers.is(2)))
                      .andExpect(jsonPath("$.bankLogoId", Matchers.is(3)));
    }

    private AccountInput getMockRequest() {
        return GenericBuilder.build(AccountInput.class)
                             .with(a -> a.setBudgetId(1L))
                             .with(a -> a.setAccountTypeId(2L))
                             .with(a -> a.setBankLogoId(3L))
                             .with(a -> a.setName("Montepio"))
                             .get();
    }
}
