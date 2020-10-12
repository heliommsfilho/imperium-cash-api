package com.github.heliommsfilho.imperium_cash.api.domain.model.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Data
public class Category extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(value = "Group Category name", required = true)
    private String name;

    @NotNull
    @Column(name = "disabled")
    @ApiModelProperty(value = "Group Category disabled", required = true)
    private Boolean disabled;

    @Column(name = "creation_date")
    @ApiModelProperty(value = "Creation date", required = true)
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    @ApiModelProperty(value = "Last update date", required = true)
    private LocalDateTime lastUpdate;
}
