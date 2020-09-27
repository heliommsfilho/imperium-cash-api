package com.github.heliommsfilho.imperium_cash.api.business.service.systemspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.DateFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.dateformat.DateFormatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateFormatServiceTests {

    @Mock
    private DateFormatRepository dateFormatRepository;

    @Test
    void dateFormatGetAll_shouldReturnDateFormatList() {
        DateFormat dateFormat1 = new DateFormat();
        dateFormat1.setPattern("dd/MM/yyyy");
        dateFormat1.setExample("20/11/2020");

        DateFormat dateFormat2 = new DateFormat();
        dateFormat2.setPattern("MM/dd/yyyy");
        dateFormat2.setExample("11/20/2020");

        DateFormat dateFormat3 = new DateFormat();
        dateFormat3.setPattern("yyyy/MM/dd");
        dateFormat3.setExample("2020/11/20");

        DateFormat dateFormat4 = new DateFormat();
        dateFormat4.setPattern("dd-MM-yyyy");
        dateFormat4.setExample("20-11-2020");

        DateFormat dateFormat5 = new DateFormat();
        dateFormat5.setPattern("MM-dd-yyyy");
        dateFormat5.setExample("11-20-2020");

        DateFormat dateFormat6 = new DateFormat();
        dateFormat6.setPattern("yyyy-MM-dd");
        dateFormat6.setExample("2020-11-20");

        DateFormat dateFormat7 = new DateFormat();
        dateFormat7.setPattern("dd.MM.yyy");
        dateFormat7.setExample("20.11.2020");

        DateFormat dateFormat8 = new DateFormat();
        dateFormat8.setPattern("MM.dd.yyyy");
        dateFormat8.setExample("11.20.2020");

        DateFormat dateFormat9 = new DateFormat();
        dateFormat9.setPattern("yyyy.MM.dd");
        dateFormat9.setExample("2020.11.20");

        when(dateFormatRepository.findAll()).thenReturn(Arrays.asList(dateFormat1, dateFormat2));
        Assertions.assertEquals(2, dateFormatRepository.findAll().size());

        Assertions.assertEquals(dateFormat1.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat1.getPattern())));
        Assertions.assertEquals(dateFormat2.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat2.getPattern())));
        Assertions.assertEquals(dateFormat3.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat3.getPattern())));
        Assertions.assertEquals(dateFormat4.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat4.getPattern())));
        Assertions.assertEquals(dateFormat5.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat5.getPattern())));
        Assertions.assertEquals(dateFormat6.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat6.getPattern())));
        Assertions.assertEquals(dateFormat7.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat7.getPattern())));
        Assertions.assertEquals(dateFormat8.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat8.getPattern())));
        Assertions.assertEquals(dateFormat9.getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormat9.getPattern())));
    }
}
