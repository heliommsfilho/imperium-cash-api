package com.github.heliommsfilho.imperium_cash.api.resource.internal;

import com.github.heliommsfilho.imperium_cash.api.business.service.internal.UserService;
import com.github.heliommsfilho.imperium_cash.api.domain.model.User;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.UserInput;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.UserOutput;
import com.github.heliommsfilho.imperium_cash.api.resource.AbstractResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = { "User" })
@SwaggerDefinition(tags = { @Tag(name = "User", description = "Operations for User resource") })
public class UserResource extends AbstractResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new User", response = UserOutput.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "User created"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> create(@RequestBody @Valid UserInput createDTO) {
        return created(userService.create(createDTO), UserOutput.class);
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific User by ID", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find User with specified ID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> getById(@PathVariable @ApiParam(value = "The User ID", required = true, type = "long", example = "0") Long id) {
        return okOrNoContent(userService.getById(id), UserOutput.class);
    }

    @GetMapping(value = "/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific User by ID", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find User with specified UUID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> getByTenantId(@PathVariable @ApiParam(value = "The User UUID", required = true, type = "string", example = "0") String uuid) {
        return okOrNoContent(userService.getByTenantUUID(uuid), UserOutput.class);
    }
}
