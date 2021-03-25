package com.github.heliommsfilho.imperium_cash.api.domain.api.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

    @ApiModelProperty(name = "Name of the User", required = true)
    private String name;

    @ApiModelProperty(name = "Email of the User", required = true)
    private String email;

    @ApiModelProperty(name = "Password of the User", required = true)
    private String password;
}
