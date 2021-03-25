package com.github.heliommsfilho.imperium_cash.api.domain.api.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountInput {

    @ApiModelProperty(name = "Budget ID", required = true)
    private Long budgetId;

    @ApiModelProperty(name = "Account Type ID", required = true)
    private Long accountTypeId;

    @ApiModelProperty(name = "Bank Logo ID", required = true)
    private Long bankLogoId;

    @ApiModelProperty(name = "Account Name", required = true)
    private String name;
}
