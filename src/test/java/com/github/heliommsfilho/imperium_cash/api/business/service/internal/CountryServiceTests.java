package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Country;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.CountryRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Country Service should")
@ExtendWith(MockitoExtension.class)
class CountryServiceTests {

    @Mock
    private CountryRepository countryRepository;
    private CountryService countryService;

    @BeforeEach
    public void init() {
        countryService = new CountryService(countryRepository);
    }

    @Test
    @DisplayName("return all Countries")
    void returnAllCountries() {
        when(countryRepository.findAll()).thenReturn(createCountries());
        Assertions.assertEquals(2, countryService.getAll().size());
    }

    @Test
    @DisplayName("return a single Country")
    void returnASingleCountry() {
        List<Country> countries = createCountries();
        when(countryRepository.findById(1L)).thenReturn(Optional.ofNullable(countries.get(0)));
        when(countryRepository.findById(2L)).thenReturn(Optional.ofNullable(countries.get(1)));

        Assertions.assertTrue(countryService.getOne(1L).isPresent());
        Assertions.assertTrue(countryService.getOne(2L).isPresent());
        Assertions.assertEquals("BR", countryService.getOne(1L).get().getCode());
        Assertions.assertEquals("PT", countryService.getOne(2L).get().getCode());
    }

    private static List<Country> createCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(GenericBuilder.build(Country.class)
                                    .with(c -> c.setCode("BR"))
                                    .with(c -> c.setName("Brasil"))
                                    .get());

        countries.add(GenericBuilder.build(Country.class)
                                    .with(c -> c.setCode("PT"))
                                    .with(c -> c.setName("Portugal"))
                                    .get());

        return countries;
    }
}
