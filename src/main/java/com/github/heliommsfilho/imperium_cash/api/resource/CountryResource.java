package com.github.heliommsfilho.imperium_cash.api.resource;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.github.heliommsfilho.imperium_cash.api.model.Country;
import com.github.heliommsfilho.imperium_cash.api.service.country.CountryService;

/**
 * The {@link CountryResource}.
 *
 * @author Hélio Márcio Filho <heliommsfilho@tutanota.com>
 */
@RestController
@RequestMapping("/country")
@Api(tags = { "Country" })
@SwaggerDefinition(tags = { @Tag(name = "Country", description = "Operations for Country resource") })
public class CountryResource implements IResource {
    
    /**
     * The {@link CountryService}.
     */
    private final CountryService countryService;
    
    /**
     * Instantiates a new {@link CountryResource}.
     *
     * @param countryService an injected {@link CountryService}.
     */
    @Autowired
    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a list of all Countries")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Countries successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "No permission to access this resource"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(this.countryService.getAll());
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific Country", response = Country.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Country successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find Country with specified ID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<Country> findById(@PathVariable @ApiParam(value = "The Country ID", required = true, type = "long", example = "0") Long id) {
        Optional<Country> country = this.countryService.getById(id);
        
        return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
