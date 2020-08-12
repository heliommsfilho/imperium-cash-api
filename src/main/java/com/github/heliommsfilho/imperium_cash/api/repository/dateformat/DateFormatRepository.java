package com.github.heliommsfilho.imperium_cash.api.repository.dateformat;

import com.github.heliommsfilho.imperium_cash.api.model.DateFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateFormatRepository extends JpaRepository<DateFormat, Long> {
}
