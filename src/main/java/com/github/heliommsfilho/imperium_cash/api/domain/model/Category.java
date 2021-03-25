package com.github.heliommsfilho.imperium_cash.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Data
public class Category extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "group_category_id")
    @JsonIgnore
    private GroupCategory groupCategory;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(value = "Group Category name", required = true)
    private String name;

    @NotNull
    @Column(name = "disabled")
    @ApiModelProperty(value = "Group Category disabled", required = true)
    private Boolean disabled;

    @JsonIgnore
    @Column(name = "creation_date")
    @ApiModelProperty(value = "Creation date", required = true)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @JsonIgnore
    @Column(name = "last_update")
    @ApiModelProperty(value = "Last update date", required = true)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
