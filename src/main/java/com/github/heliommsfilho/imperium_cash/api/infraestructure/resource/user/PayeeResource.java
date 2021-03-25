package com.github.heliommsfilho.imperium_cash.api.infraestructure.resource.user;

import com.github.heliommsfilho.imperium_cash.api.business.service.user.PayeeService;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee.PayeeCreateDTO;
import com.github.heliommsfilho.imperium_cash.api.domain.model.user.dto.payee.PayeeGeneralResponseDTO;
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
@RequestMapping("/payee")
@Api(tags = { "Payee" })
@SwaggerDefinition(tags = { @Tag(name = "Payee", description = "Operations for Payee resource")})
public class PayeeResource extends AbstractResource {

    private final PayeeService payeeService;

    @Autowired
    public PayeeResource(PayeeService payeeService) {
        this.payeeService = payeeService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Payee", response = PayeeGeneralResponseDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Payee created"),
                            @ApiResponse(code = 401, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 403, message = "Operation not permitted (lack of permission)"),
                            @ApiResponse(code = 500, message = "Internal server error (please report)")})
    public ResponseEntity<?> create(@RequestBody @Valid PayeeCreateDTO createDTO) {
        return created(payeeService.create(createDTO), PayeeGeneralResponseDTO.class);
    }
}
