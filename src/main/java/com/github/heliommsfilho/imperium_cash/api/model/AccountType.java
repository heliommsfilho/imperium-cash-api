package com.github.heliommsfilho.imperium_cash.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_type")
@Getter @Setter
@EqualsAndHashCode(of = { "id" }, callSuper = true)
public class AccountType extends BaseEntity {

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
}
