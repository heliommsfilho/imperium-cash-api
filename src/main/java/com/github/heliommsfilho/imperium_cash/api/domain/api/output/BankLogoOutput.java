package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankLogoOutput {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Bank Name")
    private String bankName;

    @ApiModelProperty(value = "Bank Logo Image")
    private String imageData;
}
