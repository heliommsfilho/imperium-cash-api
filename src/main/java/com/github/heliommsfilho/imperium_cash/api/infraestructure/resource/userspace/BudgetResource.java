package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.userspace;

import com.github.heliommsfilho.imperium_cash.api.business.service.userspace.budget.BudgetServicie;
import com.github.heliommsfilho.imperium_cash.api.domain.model.userspace.Budget;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.IResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/budget")
@Api(tags = { "Budget" })
@SwaggerDefinition(tags = { @Tag(name = "Budget", description = "Operations for Budget resource") })
public class BudgetResource implements IResource {

    private final BudgetServicie budgetServicie;

    @Autowired
    public BudgetResource(BudgetServicie budgetServicie) {
        this.budgetServicie = budgetServicie;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific Budget by ID", response = Budget.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<Budget> getById(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "0") Long id) {
        Optional<Budget> budget = budgetServicie.getById(id);

        return budget.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
