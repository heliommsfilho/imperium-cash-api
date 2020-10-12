package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.system;

import com.github.heliommsfilho.imperium_cash.api.business.service.system.currencyformat.CurrencyFormatService;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.CurrencyFormat;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.currencyformat.CurrencyFormatGeneralResponseDTO;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.AbstractResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency_format")
@Api(tags = { "Currency Format" })
@SwaggerDefinition(tags = { @Tag(name = "Currency Format", description = "Operations for Currency Format resource") })
public class CurrencyFormatResource extends AbstractResource {

    private final CurrencyFormatService currencyFormatService;

    @Autowired
    public CurrencyFormatResource(CurrencyFormatService currencyFormatService) {
        this.currencyFormatService = currencyFormatService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Currency Formats")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Currency Formats successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "No permission to access this resource"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<?>> getAll() {
        return ok(this.currencyFormatService.getAll(), CurrencyFormatGeneralResponseDTO.class);
    }
}
