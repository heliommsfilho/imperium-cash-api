package com.github.heliommsfilho.imperium_cash.api.resource;

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
class UserResourceTests extends AbstractTestResource {

    @Autowired
    private UserResource userResource;

    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(userResource);
        noContentMockMvc = getNoContentMvcMockInstance(userResource);
    }

    @Test
    void userGetById_shouldReturnHelioUser() throws Exception {
        okMockMvc.perform(get("/user/id/{id}", 1))
                 .andExpect(jsonPath("$.email", Matchers.is("heliommsfilho@gmail.com")));
    }

    @Test
    void userGetById_shouldReturnNoContent() throws Exception {
        noContentMockMvc.perform(get("/user/id/{id}", 99)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isNoContent());
    }

    @Test
    void userGetByUUID_shouldReturnHelioUser() throws Exception {
        okMockMvc.perform(get("/user/uuid/{uuid}", "63796157-4646-4627-a40f-9718e55d9216"))
                 .andExpect(jsonPath("$.email", Matchers.is("heliommsfilho@gmail.com")));
    }

    @Test
    void userGetByUUID_shouldReturnNoContent() throws Exception {
        noContentMockMvc.perform(get("/user/uuid/{uuid}", "99999999-9999-9999-9999-999999999999")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isNoContent());
    }
}
