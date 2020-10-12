package com.github.heliommsfilho.imperium_cash.api.domain.model.system;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_type")
@Data
public class AccountType extends BaseEntity {


    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_logo_id")
    private BankLogo bankLogo;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountTypeEnum;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
}
