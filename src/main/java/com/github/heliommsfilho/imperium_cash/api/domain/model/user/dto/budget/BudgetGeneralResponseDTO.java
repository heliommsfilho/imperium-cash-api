package com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.budget;

import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Account;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.GroupCategory;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.Payee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BudgetGeneralResponseDTO {

    @ApiModelProperty(value = "Budget UUID")
    private String uuid;

    @ApiModelProperty(value = "Budget User ID")
    private Long userId;

    @ApiModelProperty(value = "Budget Name")
    private String name;

    @ApiModelProperty(value = "Budget Description")
    private String description;

    @ApiModelProperty(value = "Currency ID")
    private Long currencyId;

    @ApiModelProperty(value = "Currency Format ID")
    private Long currencyFormatId;

    @ApiModelProperty(value = "Date Format ID")
    private Long dateFormatId;

    @ApiModelProperty(value = "Is active?")
    private Boolean active;

    @ApiModelProperty(value = "Creation Date")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Last Update Date")
    private LocalDateTime lastUpdate;

    @ApiModelProperty(value = "Group Categories")
    private List<GroupCategory> groupCategories;

    @ApiModelProperty(value = "Payees")
    private List<Payee> payees;

    @ApiModelProperty(value = "Accounts")
    private List<Account> accounts;
}
