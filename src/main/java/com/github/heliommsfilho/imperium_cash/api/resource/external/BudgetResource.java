package com.github.heliommsfilho.imperium_cash.api.resource.external;

import com.github.heliommsfilho.imperium_cash.api.business.service.external.BudgetService;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.BudgetInput;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.BudgetCompleteOutput;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.BudgetSummaryOutput;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.resource.AbstractResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/budget")
@Api(tags = { "Budget" })
@SwaggerDefinition(tags = { @Tag(name = "Budget", description = "Operations for Budget resource") })
public class BudgetResource extends AbstractResource {

    private final BudgetService budgetService;

    @Autowired
    public BudgetResource(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific Budget by ID (without details)", response = BudgetCompleteOutput.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> getById(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "0") Long id) {
        return okOrNoContent(budgetService.getBudget(id).orElse(null), BudgetSummaryOutput.class);
    }

    @GetMapping(value = "/{id}", params = "detailed", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific Budget by ID (without details)", response = BudgetCompleteOutput.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> getByIdWithDetails(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "0") Long id) {
        return okOrNoContent(budgetService.getBudgetWithDetails(id).orElse(null), BudgetCompleteOutput.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Budgets (without details)", response = BudgetCompleteOutput.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budgets successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<?>> getAll() {
        return ok(budgetService.getAll(), BudgetSummaryOutput.class);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Budget", response = BudgetCompleteOutput.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Budget created"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> create(@Valid BudgetInput input) {
        Budget newBudget = budgetService.create(unwrapInput(input, Budget.class));
        return created(newBudget, BudgetCompleteOutput.class);
    }
}
