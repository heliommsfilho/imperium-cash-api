package com.github.heliommsfilho.imperium_cash.api.resource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountryResourceTests extends AbstractTestResource {
    
    @Autowired
    private CountryResource countryResource;
    
    private MockMvc okMockMvc;
    private MockMvc noContentMockMvc;

    @BeforeAll
    void setup() {
        okMockMvc = getOkMvcMockInstance(countryResource);
        noContentMockMvc = getNoContentMvcMockInstance(countryResource);
    }
    
    @Test
    void findAllCountries_shouldReturnOk() throws Exception{
        okMockMvc.perform(get("/country"))
                .andExpect(jsonPath("$", hasSize(3)));
    }
    
    @Test
    void findByIdCountry_shouldReturnBrasil() throws Exception {
        okMockMvc.perform(get("/country/{id}", 1))
                .andExpect(jsonPath("$.code", is("BR")));
    }

    @Test
    void findByIdCountry_shouldReturnNothing() throws Exception {
        noContentMockMvc.perform(get("/country/{id}", 99999)
               .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().isNoContent());
    }
}
