package com.github.heliommsfilho.imperium_cash.api.service.dateformat;

import com.github.heliommsfilho.imperium_cash.api.model.DateFormat;
import com.github.heliommsfilho.imperium_cash.api.repository.dateformat.DateFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateFormatService {

    private final DateFormatRepository dateFormatRepository;

    @Autowired
    public DateFormatService(DateFormatRepository dateFormatRepository) {
        this.dateFormatRepository = dateFormatRepository;
    }

    public List<DateFormat> getAll() {
        return dateFormatRepository.findAll();
    }
}
