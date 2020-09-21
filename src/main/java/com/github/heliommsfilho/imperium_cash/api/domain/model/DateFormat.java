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
@Table(name = "date_format")
@Getter @Setter
@EqualsAndHashCode(of = { "id" }, callSuper = true)
public class DateFormat extends BaseEntity {

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "pattern")
    @ApiModelProperty(value = "Date format pattern", required = true)
    private String pattern;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "example")
    @ApiModelProperty(value = "Date format example", required = true)
    private String example;
}
