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
@DisplayName("Date Format Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DateFormatResourceTests extends AbstractTestResource {

    @Autowired
    private DateFormatResource dateFormatResource;

    private MockMvc okMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(dateFormatResource);
    }

    @Test
    @DisplayName("return JSON list of Date Formats")
    void returnJsonListDateFormats() throws Exception {
        okMockMvc.perform(get("/date_format")).andExpect(jsonPath("$", Matchers.hasSize(9)));
    }
}
