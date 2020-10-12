package com.github.heliommsfilho.imperium_cash.api.domain.model.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.Currency;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.DateFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.User;
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

    @NotBlank
    @Column(name = "uuid")
    @Size(min = 1, max = 36)
    private String uuid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 1, max = 250)
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "currency_format_id")
    private CurrencyFormat currencyFormat;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "date_format_id")
    @ApiModelProperty(value = "Date Format", required = true)
    private DateFormat dateFormat;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @NotNull
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany
    @JoinColumn(name = "budget_id")
    private List<GroupCategory> groupCategories;

    @OneToMany
    @JoinColumn(name = "budget_id")
    private List<Payee> payees;
}
