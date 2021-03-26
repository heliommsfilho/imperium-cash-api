package com.github.heliommsfilho.imperium_cash.api.domain.api.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BudgetOutput {

    @ApiModelProperty(value = "Budget UUID")
    private String uuid;

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

    @ApiModelProperty(value = "Creation Date")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Last Update Date")
    private LocalDateTime lastUpdate;

    @ApiModelProperty(value = "Group Categories")
    private List<BudgetOutput.GroupCategoryOutput> groupCategories;

    @ApiModelProperty(value = "Payees")
    private List<BudgetOutput.PayeeOutput> payees;

    @ApiModelProperty(value = "Accounts")
    private List<BudgetOutput.AccountOutput> accounts;

    @Data
    static class GroupCategoryOutput {

        private Long id;
        private String name;
        private List<GroupCategoryOutput.CategoryOutput> categories;

        @Data
        static class CategoryOutput {

            private Long id;
            private String name;
        }
    }

    @Data
    static class AccountOutput {

        private Long id;
        private String name;
    }

    @Data
    static class PayeeOutput {

        private Long id;
        private String name;
    }
}
