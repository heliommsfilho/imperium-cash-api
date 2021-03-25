package com.github.heliommsfilho.imperium_cash.api.resource.system;

import com.github.heliommsfilho.imperium_cash.api.business.service.internal.DateFormatService;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.DateFormatOutput;
import com.github.heliommsfilho.imperium_cash.api.resource.AbstractResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/date_format")
@Api(tags = { "Date Format" })
@SwaggerDefinition(tags = { @Tag(name = "Date Format", description = "Operations for Date Format resource")})
public class DateFormatResource extends AbstractResource {

    private final DateFormatService dateFormatService;

    @Autowired
    public DateFormatResource(DateFormatService dateFormatService) {
        this.dateFormatService = dateFormatService;
    }

    @GetMapping
    @ApiOperation(value = "Get a list of all Date Formats")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Currencies successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "No permission to access this resource"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<List<?>> getAll() {
        return ok(dateFormatService.getAll(), DateFormatOutput.class);
    }
}
