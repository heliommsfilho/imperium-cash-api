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
@Table(name = "country")
@Data
public class Country extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @NotBlank
    @Size(min = 1, max = 2)
    @Column(name = "code")
    @ApiModelProperty(value = "Country ISO code", required = true)
    private String code;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(value = "Country name", required = true)
    private String name;
}