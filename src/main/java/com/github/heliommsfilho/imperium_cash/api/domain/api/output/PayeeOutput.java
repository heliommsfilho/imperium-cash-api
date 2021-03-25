package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayeeOutput {

    @ApiModelProperty(name = "Payee ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "Payee name")
    private String name;
}
