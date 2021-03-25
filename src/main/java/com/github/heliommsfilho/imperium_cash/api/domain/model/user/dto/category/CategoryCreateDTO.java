package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.category;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryCreateDTO {

    @ApiModelProperty(name = "Group Category ID", required = true)
    private Long groupCategoryId;

    @ApiModelProperty(name = "Category Name", required = true)
    private String name;
}
