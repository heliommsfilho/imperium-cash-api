package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.dateformat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DateFormatGeneralResponseDTO {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Date Time Pattern")
    private String pattern;

    @ApiModelProperty(value = "Date Time Example")
    private String example;
}
