package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryOutput {

    @ApiModelProperty(name = "Category ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "Group Category ID", dataType = "long")
    private Long groupCategoryId;

    @ApiModelProperty(name = "Category Name")
    private String name;
}
