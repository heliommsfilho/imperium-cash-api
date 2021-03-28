package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BudgetSummaryOutput {

    @ApiModelProperty(value = "Budget Name")
    private String name;

    @ApiModelProperty(value = "Budget Description")
    private String description;

    @ApiModelProperty(value = "Currency Name")
    private String currencyName;

    @ApiModelProperty(value = "Currency Symbol")
    private String currencySymbol;

    @ApiModelProperty(value = "Last Update Date")
    private LocalDateTime lastUpdate;
}
