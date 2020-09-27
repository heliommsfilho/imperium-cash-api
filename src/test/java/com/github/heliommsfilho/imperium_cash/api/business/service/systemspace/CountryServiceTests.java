package com.github.heliommsfilho.imperium_cash.api.business.service.systemspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.Country;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.country.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTests {

    @Mock
    private CountryRepository countryRepository;

    @Test
    void getAll_shouldReturnCountryList() {
        Country country1 = new Country();
        country1.setCode("BR");
        country1.setName("Brasil");

        Country country2 = new Country();
        country2.setCode("PT");
        country2.setName("Portugal");

        when(countryRepository.findAll()).thenReturn(Arrays.asList(country1, country2));

        Assertions.assertEquals(2, countryRepository.findAll().size());
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void getOne_shouldReturnSingleCountry() {
        Country country1 = new Country();
        country1.setCode("BR");
        country1.setName("Brasil");

        Country country2 = new Country();
        country2.setCode("PT");
        country2.setName("Portugal");

        when(countryRepository.getOne(anyLong())).thenReturn(country1).thenReturn(country2);

        Assertions.assertEquals("BR", countryRepository.getOne(1L).getCode());
        Assertions.assertEquals("PT", countryRepository.getOne(2L).getCode());
        verify(countryRepository, times(2)).getOne(anyLong());
    }
}
