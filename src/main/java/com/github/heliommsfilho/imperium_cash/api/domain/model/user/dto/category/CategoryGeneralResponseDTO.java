package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.category;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Payee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryGeneralResponseDTO {

    @ApiModelProperty(name = "Category ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "Group Category ID", dataType = "long")
    private Long groupCategoryId;

    @ApiModelProperty(name = "Category Name")
    private String name;
}
