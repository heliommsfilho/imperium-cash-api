package com.github.heliommsfilho.imperium_cash.api.helper;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Tag("Helpers")
@DisplayName("Generic Builder should")
class GenericBuilderTests {

    @Test
    @DisplayName("create a new instance successfully")
    void createInstance() {
        Country country = GenericBuilder.build(Country.class)
                                        .with(c -> c.setId(99L))
                                        .with(c -> c.setCode("CN"))
                                        .with(c -> c.setName("Country Name"))
                                        .get();

        Assertions.assertEquals(99L, country.getId());
        Assertions.assertEquals("CN", country.getCode());
        Assertions.assertEquals("Country Name", country.getName());
    }

    @Test
    @DisplayName("throw exception when creating instance")
    void throwsException() {
        Assertions.assertNull(GenericBuilder.build(LocalDate.class).get());
    }
}
