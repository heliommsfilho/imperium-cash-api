package com.github.heliommsfilho.imperium_cash.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "budget")
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

    @JsonIgnore
    @NotNull
    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @NotNull
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
    private List<GroupCategory> groupCategories;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
    private List<Payee> payees;

    @OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
    private List<Account> accounts;
}
