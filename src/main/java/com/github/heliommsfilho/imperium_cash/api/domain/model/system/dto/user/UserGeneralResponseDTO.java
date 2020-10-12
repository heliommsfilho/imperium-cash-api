package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserGeneralResponseDTO {

    @ApiModelProperty(value = "User UUID")
    private String tenantUUID;

    @ApiModelProperty(value = "User Name")
    private String name;

    @ApiModelProperty(value = "User Email")
    private String email;

    @ApiModelProperty(value = "User Password")
    private String password;
}
