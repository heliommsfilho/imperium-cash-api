package com.github.heliommsfilho.imperium_cash.api.resource.external;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Tag("IntegrationTests")
@DisplayName("Budget Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BudgetResourceTests extends AbstractTestResource {

    @Autowired
    private BudgetResource budgetResource;

    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(budgetResource);
        noContentMockMvc = getNoContentMvcMockInstance(budgetResource);
    }

    @Test
    @DisplayName("return JSON Budget not fetched")
    void returnBudgetNotFetched() throws Exception {
        okMockMvc.perform(get("/budget/{id}", 1))
                 .andExpect(jsonPath("$.name", Matchers.is("Finances in Portugal")));
    }

    @Test
    @DisplayName("return JSON Budget fetched")
    void returnBudgetFetched() throws Exception {
        okMockMvc.perform(get("/budget/{id}?detailed", 1))
                 .andExpect(jsonPath("$.name", Matchers.is("Finances in Portugal")))
                 .andExpect(jsonPath("$.payees", Matchers.hasSize(3)))
                 .andExpect(jsonPath("$.groupCategories", Matchers.hasSize(3)))
                 .andExpect(jsonPath("$.groupCategories[0].categories", Matchers.hasSize(2)))
                 .andExpect(jsonPath("$.groupCategories[1].categories", Matchers.hasSize(1)))
                 .andExpect(jsonPath("$.groupCategories[2].categories", Matchers.hasSize(1)));

    }

    @Test
    @DisplayName("return JSON list of Budgets not fetched")
    void returnJsonListBudgetsNotFetched() throws Exception {
        okMockMvc.perform(get("/budget"))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Finances in Portugal")))
                .andExpect(jsonPath("$[1].name", Matchers.is("Finances in Brazil")));
    }

    @Test
    @DisplayName("return 'No Content' status for nonexistent Budget ID")
    void returnNoContentNonExistentBudgetId() throws Exception {
        noContentMockMvc.perform(get("/budget/{id}", 99)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isNoContent());
    }
}
