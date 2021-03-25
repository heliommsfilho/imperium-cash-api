package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.user;

import com.github.heliommsfilho.imperium_cash.api.business.service.user.BudgetServicie;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.budget.BudgetCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.budget.BudgetGeneralResponseDTO;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/budget")
@Api(tags = { "Budget" })
@SwaggerDefinition(tags = { @Tag(name = "Budget", description = "Operations for Budget resource") })
public class BudgetResource extends AbstractResource {

    private final BudgetServicie budgetServicie;

    @Autowired
    public BudgetResource(BudgetServicie budgetServicie) {
        this.budgetServicie = budgetServicie;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific Budget by ID (without details)", response = BudgetGeneralResponseDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> getById(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "0") Long id) {
        return okOrNoContent(budgetServicie.getBudget(id), BudgetGeneralResponseDTO.class);
    }

    @GetMapping(value = "/{id}", params = "detailed", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific Budget by ID (without details)", response = BudgetGeneralResponseDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> getByIdWithDetails(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "0") Long id) {
        return okOrNoContent(budgetServicie.getBudgetWithDetails(id), BudgetGeneralResponseDTO.class);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Budgets (without details)", response = BudgetGeneralResponseDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budgets successfully retrieved"),
            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<?>> getAll() {
        return ok(budgetServicie.getAll(), BudgetGeneralResponseDTO.class);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Budget", response = BudgetGeneralResponseDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Budget created"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> create(@Valid BudgetCreateDTO createDTO) {
        return created(budgetServicie.create(createDTO), BudgetGeneralResponseDTO.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", params = "activate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Activate a Budget ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully activated"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> activate(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "1") Long id) {
        budgetServicie.activate(id);

        return noContent();
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}", params = "inactivate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Inactivate a Budget ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Budget successfully inactivated"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find Budget with specified ID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> inactivate(@PathVariable @ApiParam(value = "The Budget ID", required = true, type = "long", example = "1") Long id) {
        budgetServicie.inactivate(id);

        return noContent();
    }
}
