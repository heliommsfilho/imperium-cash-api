package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.systemspace;

import com.github.heliommsfilho.imperium_cash.api.domain.model.User;
import com.github.heliommsfilho.imperium_cash.api.business.service.systemspace.user.UserService;
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
@RequestMapping("/user")
@Api(tags = { "User" })
@SwaggerDefinition(tags = { @Tag(name = "User", description = "Operations for User resource") })
public class UserResource implements IResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific User by ID", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find User with specified ID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<User> getById(@PathVariable @ApiParam(value = "The User ID", required = true, type = "long", example = "0") Long id) {
        Optional<User> user = userService.getById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a specific User by ID", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User successfully retrieved"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 404, message = "Cannot find User with specified UUID"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<User> getByTenantId(@PathVariable @ApiParam(value = "The User UUID", required = true, type = "string", example = "0") String uuid) {
        Optional<User> user = userService.getByTenantUUID(uuid);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
