package com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.banklogo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankLogoGeneralResponseDTO {

    @ApiModelProperty(value = "The Resource ID")
    private Long id;

    @ApiModelProperty(value = "Bank Name")
    private String bankName;

    @ApiModelProperty(value = "Bank Logo Image")
    private String imageData;
}
