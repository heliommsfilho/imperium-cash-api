package com.github.heliommsfilho.imperium_cash.api.resource.system;

import com.github.heliommsfilho.imperium_cash.api.business.service.internal.BankLogoService;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.BankLogoOutput;
import com.github.heliommsfilho.imperium_cash.api.resource.AbstractResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank_logo")
@Api(tags = { "Bank Logos" })
@SwaggerDefinition(tags = { @Tag(name = "Bank Logos", description = "Operations for Bank Logo resource") })
public class BankLogoResource extends AbstractResource {

    private final BankLogoService bankLogoService;

    @Autowired
    public BankLogoResource(BankLogoService bankLogoService) {
        this.bankLogoService = bankLogoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Bank Logos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Bank Logos successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "No permission to access this resource"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<?>> getAll() {
        return ok(this.bankLogoService.getAll(), BankLogoOutput.class);
    }
}
