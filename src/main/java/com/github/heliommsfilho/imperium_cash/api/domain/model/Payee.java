package com.github.heliommsfilho.imperium_cash.api.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payee")
public class Payee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @Column(name = "name")
    @ApiModelProperty(value = "Payee name", required = true)
    private String name;

    @NotNull
    @Column(name = "disabled")
    @ApiModelProperty(value = "Payee disabled", required = true)
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
