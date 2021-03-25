package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.DateFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.DateFormatRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Date Format Service should")
@ExtendWith(MockitoExtension.class)
class DateFormatServiceTests {

    @Mock
    private DateFormatRepository dateFormatRepository;
    private DateFormatService dateFormatService;

    @BeforeEach
    public void init() {
        dateFormatService = new DateFormatService(dateFormatRepository);
    }

    @Test
    @DisplayName("return all Date Formats")
    void returnAllDateFormats() {
        when(dateFormatRepository.findAll()).thenReturn(createDateFormats());
        List<DateFormat> dateFormats = dateFormatService.getAll();

        Assertions.assertEquals(9, dateFormats.size());
        Assertions.assertEquals(dateFormats.get(0).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(0).getPattern())));
        Assertions.assertEquals(dateFormats.get(1).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(1).getPattern())));
        Assertions.assertEquals(dateFormats.get(2).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(2).getPattern())));
        Assertions.assertEquals(dateFormats.get(3).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(3).getPattern())));
        Assertions.assertEquals(dateFormats.get(4).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(4).getPattern())));
        Assertions.assertEquals(dateFormats.get(5).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(5).getPattern())));
        Assertions.assertEquals(dateFormats.get(6).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(6).getPattern())));
        Assertions.assertEquals(dateFormats.get(7).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(7).getPattern())));
        Assertions.assertEquals(dateFormats.get(8).getExample(), LocalDate.of(2020, 11, 20).format(DateTimeFormatter.ofPattern(dateFormats.get(8).getPattern())));
    }

    private static List<DateFormat> createDateFormats() {
        List<DateFormat> dateFormats = new ArrayList<>();

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("dd/MM/yyyy"))
                   .with(d -> d.setExample("20/11/2020"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("MM/dd/yyyy"))
                   .with(d -> d.setExample("11/20/2020"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("yyyy/MM/dd"))
                   .with(d -> d.setExample("2020/11/20"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("dd-MM-yyyy"))
                   .with(d -> d.setExample("20-11-2020"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("MM-dd-yyyy"))
                   .with(d -> d.setExample("11-20-2020"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("yyyy-MM-dd"))
                   .with(d -> d.setExample("2020-11-20"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("dd.MM.yyy"))
                   .with(d -> d.setExample("20.11.2020"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("MM.dd.yyyy"))
                   .with(d -> d.setExample("11.20.2020"))
                   .get());

        dateFormats.add(GenericBuilder.build(DateFormat.class)
                   .with(d -> d.setPattern("yyyy.MM.dd"))
                   .with(d -> d.setExample("2020.11.20"))
                   .get());

        return dateFormats;
    }
}
