package com.github.heliommsfilho.imperium_cash.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "currency")
@Getter @Setter
@EqualsAndHashCode(of = {"id"}, callSuper = true)
public class Currency extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(value = "Currency Name", required = true)
    private String name;

    @NotBlank
    @Size(min = 1, max = 3)
    @Column(name = "code")
    @ApiModelProperty(value = "Currency Code", required = true)
    private String code;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "symbol")
    @ApiModelProperty(value = "Currency Symbol", required = true)
    private String symbol;
}
