package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CurrencyOutput {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Currency Name")
    private String name;

    @ApiModelProperty(value = "Currency Code")
    private String code;

    @ApiModelProperty(value = "Currency Symbol")
    private String symbol;
}
