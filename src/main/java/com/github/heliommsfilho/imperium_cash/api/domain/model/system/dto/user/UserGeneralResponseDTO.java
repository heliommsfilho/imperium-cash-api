package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserGeneralResponseDTO {

    @ApiModelProperty(name = "User UUID")
    private String uuid;

    @ApiModelProperty(name = "Name of the User", required = true)
    private String name;

    @ApiModelProperty(name = "Email of the User", required = true)
    private String email;
}
