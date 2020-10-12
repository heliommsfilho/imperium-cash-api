package com.github.heliommsfilho.imperium_cash.api.infraestructure.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import springfox.documentation.spring.web.plugins.Docket;

@Tag("Configuration")
@DisplayName("Swagger Configuration should")
@SpringBootTest
class SwaggerConfigTests {

    @Autowired
    private Docket docket;

    @Test
    @DisplayName("return a not null Bean")
    void returnNotNullBean() {
        assertThat(docket, is(not(nullValue())));
    }
}