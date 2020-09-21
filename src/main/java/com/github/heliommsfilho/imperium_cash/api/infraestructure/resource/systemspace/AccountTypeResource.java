package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.AccountType;
import com.github.heliommsfilho.imperium_cash.api.business.service.systemspace.accounttype.AccountTypeService;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.IResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account_type")
@Api(tags = { "Account Type" })
@SwaggerDefinition(tags = { @Tag(name = "Account Type", description = "Operations for Account Type resource") })
public class AccountTypeResource implements IResource {

    private final AccountTypeService accountTypeService;

    @Autowired
    public AccountTypeResource(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Account Types")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Account Types successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "No permission to access this resource"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<AccountType>> getAll() {
        return ResponseEntity.ok(accountTypeService.getAll());
    }
}
