package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayeeGeneralResponseDTO {

    @ApiModelProperty(name = "Payee ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "Payee name")
    private String name;
}
