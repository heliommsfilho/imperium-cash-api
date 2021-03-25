package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DateFormatOutput {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Date Time Pattern")
    private String pattern;

    @ApiModelProperty(value = "Date Time Example")
    private String example;
}
