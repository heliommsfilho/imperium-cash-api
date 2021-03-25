package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayeeCreateDTO {

    @ApiModelProperty(name = "Budget ID", required = true)
    private Long budgetId;

    @ApiModelProperty(name = "Payee name", required = true)
    private String name;
}
