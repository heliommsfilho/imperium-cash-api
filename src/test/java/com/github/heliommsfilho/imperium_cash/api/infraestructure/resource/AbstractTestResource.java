package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractTestResource {

    public static MockMvc getOkMockMvc(AbstractResource resource) {
        return MockMvcBuilders.standaloneSetup(resource)
                              .alwaysDo(print())
                              .alwaysExpect(status().isOk())
                              .alwaysExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                              .build();
    }

    public static MockMvc getCreatedMockMvc(AbstractResource resource) {
        return MockMvcBuilders.standaloneSetup(resource)
                              .alwaysDo(print())
                              .alwaysExpect(status().isCreated())
                              .build();
    }

    public static MockMvc getNoContentMvcMockInstance(AbstractResource resource) {
        return MockMvcBuilders.standaloneSetup(resource)
                              .alwaysDo(print())
                              .alwaysExpect(status().isNoContent())
                              .build();
    }
}
