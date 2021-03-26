package com.github.heliommsfilho.imperium_cash.api.domain.api.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PayeeInput {

    @NotNull
    @ApiModelProperty(name = "Budget ID", required = true)
    private Long budgetId;

    @NotBlank
    @Size(min = 1, max = 50)
    @ApiModelProperty(name = "Payee name", required = true)
    private String name;
}
