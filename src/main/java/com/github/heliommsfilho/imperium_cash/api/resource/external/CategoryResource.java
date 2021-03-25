package com.github.heliommsfilho.imperium_cash.api.resource.external;

import com.github.heliommsfilho.imperium_cash.api.business.service.external.CategoryService;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.CategoryInput;
import com.github.heliommsfilho.imperium_cash.api.domain.api.output.CategoryOutput;
import com.github.heliommsfilho.imperium_cash.api.resource.AbstractResource;
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
@RequestMapping("/category")
@Api(tags = { "Category" })
@SwaggerDefinition(tags = { @Tag(name = "Category", description = "Operations for Category resource")})
public class CategoryResource extends AbstractResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Category", response = CategoryOutput.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Category created"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> create(@RequestBody @Valid CategoryInput createDTO) {
        return created(categoryService.create(createDTO), CategoryOutput.class);
    }
}
