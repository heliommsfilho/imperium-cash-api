package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountOutput {

    @ApiModelProperty(name = "Account ID", required = true)
    private Long id;

    @ApiModelProperty(name = "Budget ID", required = true)
    private Long budgetId;

    @ApiModelProperty(name = "Account Type ID", required = true)
    private Long accountTypeId;

    @ApiModelProperty(name = "Bank Logo ID", required = true)
    private Long bankLogoId;

    @ApiModelProperty(name = "Account Name", required = true)
    private String name;
}
