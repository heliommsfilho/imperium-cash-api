package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.accounttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.AccountTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountTypeGeneralResponseDTO {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Account Type Name")
    private String name;

    @JsonProperty(value = "account_type")
    @ApiModelProperty(value = "Account Type")
    private AccountTypeEnum accountTypeEnum;

    @JsonProperty(value = "image_data")
    @ApiModelProperty(value = "Default Bank Logo")
    private String bankLogoImageData;
}