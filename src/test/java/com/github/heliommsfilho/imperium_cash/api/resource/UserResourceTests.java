package com.github.heliommsfilho.imperium_cash.api.resource;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserResourceTests extends AbstractTestResource {

    @Autowired
    private UserResource userResource;

    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(userResource);
    }

    @Test
    public void userGetById_shouldReturnHelioUser() throws Exception {
        okMockMvc.perform(get("/user/id/{id}", 1))
                 .andExpect(jsonPath("$.email", Matchers.is("heliommsfilho@gmail.com")));
    }

    @Test
    public void userGetByUUID_shouldReturnHelioUser() throws Exception {
        okMockMvc.perform(get("/user/uuid/{uuid}", "63796157-4646-4627-a40f-9718e55d9216"))
                 .andExpect(jsonPath("$.email", Matchers.is("heliommsfilho@gmail.com")));
    }
}
