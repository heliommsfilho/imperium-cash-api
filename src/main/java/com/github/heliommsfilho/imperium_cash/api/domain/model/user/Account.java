package com.github.heliommsfilho.imperium_cash.api.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.AccountType;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.BankLogo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "budget_id")
    @ApiModelProperty(name = "Budget name", required = true)
    @JsonIgnore
    private Budget budget;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_type_id")
    @ApiModelProperty(name = "Account Type name", required = true)
    private AccountType accountType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_logo_id")
    @ApiModelProperty(name = "Bank logo", required = true)
    private BankLogo bankLogo;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(name = "Account name", required = true)
    private String name;

    @NotNull
    @Column(name = "disabled")
    @ApiModelProperty(name = "Account disabled", required = true)
    private Boolean disabled;

    @Column(name = "creation_date")
    @ApiModelProperty(value = "Creation date", required = true)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    @ApiModelProperty(value = "Last update date", required = true)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
