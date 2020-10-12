package com.github.heliommsfilho.imperium_cash.api.domain.model.system;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bank_logo")
@Data
public class BankLogo extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "bank_name")
    private String bankName;

    @NotBlank
    @Size(min = 1)
    @Column(name = "image_data")
    private String imageData;
}
