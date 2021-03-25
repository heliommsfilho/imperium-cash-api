package com.github.heliommsfilho.imperium_cash.api.resource.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.UserInput;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Tag("Integration Tests")
@DisplayName("User Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserResourceTests extends AbstractTestResource {

    @Autowired
    private UserResource userResource;

    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;
    private MockMvc createdMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(userResource);
        noContentMockMvc = getNoContentMvcMockInstance(userResource);
        createdMockMvc = getCreatedMockMvc(userResource);
    }

    @Test
    @DisplayName("create a new User")
    @Transactional
    void createNewUser() throws Exception {
        UserInput userInput = new UserInput("Jonh Doe", "jonh.doe@gmail.com", "Some BCrypt Hash");
        String json = (new ObjectMapper()).writeValueAsString(userInput);

        createdMockMvc.perform(post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                      .andExpect(jsonPath("$.uuid", Matchers.not(Matchers.blankOrNullString())))
                      .andExpect(jsonPath("$.name", Matchers.is("Jonh Doe")))
                      .andExpect(jsonPath("$.email", Matchers.is("jonh.doe@gmail.com")));
    }

    @Test
    @DisplayName("return JSON User for given ID")
    void returnJsonUserForId() throws Exception {
        okMockMvc.perform(get("/user/id/{id}", 1))
                 .andExpect(jsonPath("$.email", Matchers.is("heliommsfilho@gmail.com")));
    }

    @Test
    @DisplayName("return 'No Content' status for nonexistent User ID")
    void returnNoContentNonExistentUserId() throws Exception {
        noContentMockMvc.perform(get("/user/id/{id}", 99)).andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("return JSON User for given UUID")
    void returnJsonUserForUuid() throws Exception {
        okMockMvc.perform(get("/user/uuid/{uuid}", "63796157-4646-4627-a40f-9718e55d9216"))
                 .andExpect(jsonPath("$.email", Matchers.is("heliommsfilho@gmail.com")));
    }

    @Test
    @DisplayName("return 'No Content' status for nonexistent User UUID")
    void returnNoContentNonExistentUserUuid() throws Exception {
        noContentMockMvc.perform(get("/user/uuid/{uuid}", "99999999-9999-9999-9999-999999999999")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isNoContent());
    }
}
