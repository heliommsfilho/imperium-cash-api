package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserOutput {

    @ApiModelProperty(name = "User UUID")
    private String uuid;

    @ApiModelProperty(name = "Name of the User", required = true)
    private String name;

    @ApiModelProperty(name = "Email of the User", required = true)
    private String email;
}
