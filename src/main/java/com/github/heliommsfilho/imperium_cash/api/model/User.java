package com.github.heliommsfilho.imperium_cash.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Getter @Setter
@EqualsAndHashCode(of = { "id" }, callSuper = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 36, max = 36)
    @Column(name = "tenant_uuid")
    @ApiModelProperty(value = "Unique User UUID", required = false)
    private String tenantUUID;

    @Size(min = 0, max = 50)
    @Column(name = "name")
    @ApiModelProperty(value = "Name of the User", required = false)
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "email")
    @ApiModelProperty(name = "Email of the User", required = true)
    private String email;

    @NotBlank
    @Size(min = 1, max = 64)
    @Column(name = "password")
    @ApiModelProperty(value = "Password of the User", required = true)
    private String password;
}
