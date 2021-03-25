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
@Table(name = "group_category")
public class GroupCategory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "budget_id")
    @JsonIgnore
    private Budget budget;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_category_id")
    private List<Category> categories;
}
