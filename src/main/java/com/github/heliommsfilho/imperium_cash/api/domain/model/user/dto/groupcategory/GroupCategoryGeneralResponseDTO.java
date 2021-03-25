package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.groupcategory;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupCategoryGeneralResponseDTO {

    @ApiModelProperty(name = "Group Category ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "Group Category Name")
    private String name;
}
