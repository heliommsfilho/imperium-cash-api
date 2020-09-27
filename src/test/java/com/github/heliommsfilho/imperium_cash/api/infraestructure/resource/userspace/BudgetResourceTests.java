package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.userspace;

import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BudgetResourceTests extends AbstractTestResource {

    @Autowired
    private BudgetResource budgetResource;

    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(budgetResource);
        noContentMockMvc = getNoContentMvcMockInstance(budgetResource);
    }

    @Test
    void budgetGetById_shouldReturnBudget() throws Exception {
        okMockMvc.perform(get("/budget/{id}", 1))
                .andExpect(jsonPath("$.name", Matchers.is("Finances in Portugal")))
                .andExpect(jsonPath("$.payees", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.groupCategories", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.groupCategories[0].categories", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.groupCategories[1].categories", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.groupCategories[2].categories", Matchers.hasSize(1)));
    }

    @Test
    void budgetGetById_shouldReturnNoContent() throws Exception {
        noContentMockMvc.perform(get("/budget/{id}", 99)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }
}
