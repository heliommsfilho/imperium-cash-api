package com.github.heliommsfilho.imperium_cash.api.resource.internal;

import com.github.heliommsfilho.imperium_cash.api.business.service.internal.CurrencyService;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.CurrencyOutput;
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
@RequestMapping("/currency")
@Api(tags = { "Currency" })
@SwaggerDefinition(tags =  { @Tag(name = "Currency", description = "Operations for Currency resource")})
public class CurrencyResource extends AbstractResource {
    
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyResource(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Currencies")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Currencies successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "No permission to access this resource"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<?>> findAll() {
        return ok(this.currencyService.getAll(), CurrencyOutput.class);
    }

}
