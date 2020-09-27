package com.github.heliommsfilho.imperium_cash.api.infraestructure.helper;

import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class GenericBuilderTests {

    @Test
    void constructInstanceWithGenericBuilder_shouldSucceed() {
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
    void constructInstanceWithGenericBuilder_shouldThrowsException() {
        Assertions.assertNull(GenericBuilder.build(LocalDate.class).get());
    }
}