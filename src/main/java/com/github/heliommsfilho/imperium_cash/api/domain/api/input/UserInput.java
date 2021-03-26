package com.github.heliommsfilho.imperium_cash.api.domain.api.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

    @NotBlank
    @Size(min = 1, max = 50)
    @ApiModelProperty(name = "Name of the User", required = true)
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @ApiModelProperty(name = "Email of the User", required = true)
    private String email;


    @NotBlank
    @Size(min = 1, max = 64)
    @ApiModelProperty(name = "Password of the User", required = true)
    private String password;
}
