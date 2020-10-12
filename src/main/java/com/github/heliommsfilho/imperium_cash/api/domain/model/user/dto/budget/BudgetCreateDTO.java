package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.budget;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BudgetCreateDTO {

    @ApiModelProperty(value = "The Resource ID", dataType = "long")
    private Long id;

    @ApiModelProperty(value = "Budget UUID")
    private String uuid;

    @ApiModelProperty(value = "Budget User ID")
    private Long userId;

    @ApiModelProperty(value = "Budget Name")
    private String name;

    @ApiModelProperty(value = "Budget Description")
    private String description;

    @ApiModelProperty(value = "Currency ID")
    private Long currencyId;

    @ApiModelProperty(value = "Currency Format ID")
    private Long currencyFormatId;

    @ApiModelProperty(value = "Date Format ID")
    private Long dateFormatId;
}
