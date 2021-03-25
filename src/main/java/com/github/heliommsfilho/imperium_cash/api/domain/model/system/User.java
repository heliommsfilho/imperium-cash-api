package com.github.heliommsfilho.imperium_cash.api.domain.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user", schema = "imperium_cash")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 36, max = 36)
    @Column(name = "tenant_uuid")
    private String tenantUUID;

    @Size(min = 0, max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @JsonIgnore
    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
