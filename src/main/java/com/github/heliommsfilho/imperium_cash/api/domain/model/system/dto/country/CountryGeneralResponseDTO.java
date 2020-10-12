package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.country;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CountryGeneralResponseDTO {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Country ISO Code")
    private String code;

    @ApiModelProperty(value = "Country Name")
    private String name;
}
