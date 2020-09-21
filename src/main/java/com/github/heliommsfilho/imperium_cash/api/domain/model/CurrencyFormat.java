package com.github.heliommsfilho.imperium_cash.api.domain.model;

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
@Table(name = "currency_format")
@Getter @Setter
public class CurrencyFormat extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "pattern")
    @ApiModelProperty(value = "Currency format pattern", required = true)
    private String pattern;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "example_positive")
    @ApiModelProperty(value = "Currency format example (positive)", required = true)
    private String examplePositive;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "example_negative")
    @ApiModelProperty(value = "Currency format example (negative)", required = true)
    private String exampleNegative;
}
