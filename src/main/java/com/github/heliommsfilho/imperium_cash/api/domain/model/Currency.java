package com.github.heliommsfilho.imperium_cash.api.domain.model;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "currency")
@Data
public class Currency extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 1, max = 3)
    @Column(name = "code")
    private String code;

    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "symbol")
    private String symbol;
}
