package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.user;

import com.github.heliommsfilho.imperium_cash.api.business.service.user.AccountService;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.account.AccountCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.account.AccountGeneralResponseDTO;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@Api(tags = { "Account" })
@SwaggerDefinition(tags = { @Tag(name = "Account", description = "Operations for Account resource")})
public class AccountResource extends AbstractResource {

    private final AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Account", response = AccountGeneralResponseDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Account created"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> create(@RequestBody @Valid AccountCreateDTO createDTO) {
        return created(accountService.create(createDTO), AccountGeneralResponseDTO.class);
    }
}
