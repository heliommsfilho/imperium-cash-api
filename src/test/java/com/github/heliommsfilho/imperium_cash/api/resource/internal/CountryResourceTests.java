package com.github.heliommsfilho.imperium_cash.api.resource.internal;

import com.github.heliommsfilho.imperium_cash.api.resource.AbstractTestResource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Tag("Integration Tests")
@DisplayName("Country Resource should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CountryResourceTests extends AbstractTestResource {
    
    @Autowired
    private CountryResource countryResource;
    
    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMockMvc(countryResource);
        noContentMockMvc = getNoContentMvcMockInstance(countryResource);
    }
    
    @Test
    @DisplayName("return JSON list of Countries")
    void returnJsonListCountries() throws Exception{
        okMockMvc.perform(get("/country")).andExpect(jsonPath("$", hasSize(3)));
    }
    
    @Test
    @DisplayName("return JSON Country 'Brasil'")
    void returnJsonCountryBrasil() throws Exception {
        okMockMvc.perform(get("/country/{id}", 1)).andExpect(jsonPath("$.code", is("BR")));
    }

    @Test
    @DisplayName("return 'No Content' status for nonexistent Country")
    void returnNoContentNonExistentCountry() throws Exception {
        noContentMockMvc.perform(get("/country/{id}", 99999)).andExpect(status().isNoContent());
    }
}
