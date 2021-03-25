package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.groupcategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupCategoryCreateDTO {

    @ApiModelProperty(name = "Budget ID", required = true)
    private Long budgetId;

    @ApiModelProperty(name = "Group Category Name", required = true)
    private String name;
}
