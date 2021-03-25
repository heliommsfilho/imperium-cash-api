package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.category.CategoryCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.GenericBuilder;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractTestResource;
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
@DisplayName("Category Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryResourceTests extends AbstractTestResource {

    @Autowired
    private CategoryResource categoryResource;

    private MockMvc createdMockMvc;

    @BeforeAll
    void setup() {
        createdMockMvc = getCreatedMockMvc(categoryResource);
    }

    @Test
    @DisplayName("create a new Category")
    @Transactional
    void createNewCategory() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(getMockRequest());

        createdMockMvc.perform(post("/category")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                      .andExpect(jsonPath("$.name", Matchers.is("Transporte")));
    }

    private CategoryCreateDTO getMockRequest() {
        return GenericBuilder.build(CategoryCreateDTO.class)
                             .with(p -> p.setGroupCategoryId(2L))
                             .with(p -> p.setName("Transporte"))
                             .get();
    }
}
