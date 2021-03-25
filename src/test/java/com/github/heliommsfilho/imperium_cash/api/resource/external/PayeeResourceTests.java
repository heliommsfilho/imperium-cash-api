package com.github.heliommsfilho.imperium_cash.api.resource.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.PayeeInput;
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
@DisplayName("Payee Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PayeeResourceTests extends AbstractTestResource {

    @Autowired
    private PayeeResource payeeResource;

    private MockMvc createdMockMvc;

    @BeforeAll
    void setup() {
        createdMockMvc = getCreatedMockMvc(payeeResource);
    }

    @Test
    @DisplayName("create a new Payee")
    @Transactional
    void createNewPayee() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(getMockRequest());

        createdMockMvc.perform(post("/payee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                      .andExpect(jsonPath("$.name", Matchers.is("Pingo Doce")));
    }

    private PayeeInput getMockRequest() {
        return GenericBuilder.build(PayeeInput.class)
                             .with(p -> p.setBudgetId(1L))
                             .with(p -> p.setName("Pingo Doce"))
                             .get();
    }
}
