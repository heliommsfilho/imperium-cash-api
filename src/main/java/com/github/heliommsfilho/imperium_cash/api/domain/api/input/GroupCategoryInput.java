package com.github.heliommsfilho.imperium_cash.api.domain.api.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupCategoryInput {

    @ApiModelProperty(name = "Budget ID", required = true)
    private Long budgetId;

    @ApiModelProperty(name = "Group Category Name", required = true)
    private String name;
}
