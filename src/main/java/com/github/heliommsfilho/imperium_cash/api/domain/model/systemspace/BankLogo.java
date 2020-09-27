package com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bank_logo")
@Data
public class BankLogo extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "bank_name")
    @ApiModelProperty(value = "Bank Name", required = true)
    private String bankName;

    @NotBlank
    @Size(min = 1)
    @Column(name = "image_data")
    @ApiModelProperty(value = "Base64 Image Data", required = true)
    private String imageData;
}
