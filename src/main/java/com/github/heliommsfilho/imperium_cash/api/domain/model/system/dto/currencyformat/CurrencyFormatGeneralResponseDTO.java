package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.currencyformat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CurrencyFormatGeneralResponseDTO {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Currency Format Pattern")
    private String pattern;

    @ApiModelProperty(value = "Currency Format Example (positive)")
    private String examplePositive;

    @ApiModelProperty(value = "Currency Format Example (negative)")
    private String exampleNegative;
}
