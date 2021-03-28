package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BudgetCompleteOutput {

    @ApiModelProperty(value = "Budget UUID")
    private String uuid;

    @ApiModelProperty(value = "Budget Name")
    private String name;

    @ApiModelProperty(value = "Budget Description")
    private String description;

    @ApiModelProperty(value = "Currency Code")
    private String currencyCode;

    @ApiModelProperty(value = "Currency Symbol")
    private String currencySymbol;

    @ApiModelProperty(value = "Currency Format Pattern")
    private String currencyFormatPattern;

    @ApiModelProperty(value = "Date Format Pattern")
    private String dateFormatPattern;

    @ApiModelProperty(value = "Creation Date")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Last Update Date")
    private LocalDateTime lastUpdate;

    @ApiModelProperty(value = "Group Categories")
    private List<BudgetCompleteOutput.GroupCategoryOutput> groupCategories;

    @ApiModelProperty(value = "Payees")
    private List<BudgetCompleteOutput.PayeeOutput> payees;

    @ApiModelProperty(value = "Accounts")
    private List<BudgetCompleteOutput.AccountOutput> accounts;

    @Data
    static class GroupCategoryOutput {

        private String name;
        private List<GroupCategoryOutput.CategoryOutput> categories;

        @Data
        static class CategoryOutput {

            private String name;
        }
    }

    @Data
    static class AccountOutput {

        private String name;
    }

    @Data
    static class PayeeOutput {

        private String name;
    }
}
