package com.github.heliommsfilho.imperium_cash.api.domain.model.userspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.Currency;
import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.DateFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.systemspace.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "budget")
@Data
public class Budget extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "Budget User's ID", required = true)
    private User user;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(value = "Budget name", required = true)
    private String name;

    @NotBlank
    @Size(min = 1, max = 250)
    @Column(name = "description")
    @ApiModelProperty(value = "Budget description", required = true)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "currency_id")
    @ApiModelProperty(value = "Currency", required = true)
    private Currency currency;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "currency_format_id")
    @ApiModelProperty(value = "Currency Format", required = true)
    private CurrencyFormat currencyFormat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "date_format_id")
    @ApiModelProperty(value = "Date Format", required = true)
    private DateFormat dateFormat;

    @NotNull
    @Column(name = "creation_date")
    @ApiModelProperty(value = "Creation date", required = true)
    private LocalDateTime creationDate;

    @NotNull
    @Column(name = "last_update")
    @ApiModelProperty(value = "Last update date", required = true)
    private LocalDateTime lastUpdate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "budget_id")
    private List<GroupCategory> groupCategories;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "budget_id")
    private List<Payee> payees;
}
