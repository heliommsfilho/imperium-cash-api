package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.PayeeInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.PayeeRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Payee Service should")
@ExtendWith(MockitoExtension.class)
public class PayeeServiceTests {

    @Mock
    private PayeeRepository repository;
    private PayeeService service;

    @BeforeEach
    public void init() {
        this.service = new PayeeService(repository);
    }

    @Test
    @DisplayName("create a new Payee")
    void createNewPayee() {
        when(repository.save(any())).thenReturn(getMockResoultCreateDTO());
        Payee savedPayee = service.create(getMockCreateDTO());

        Assertions.assertEquals(1L, savedPayee.getId());
        Assertions.assertEquals(2L, savedPayee.getBudget().getId());
        Assertions.assertEquals("Payee Name", savedPayee.getName());
    }

    private PayeeInput getMockCreateDTO() {
        return GenericBuilder.build(PayeeInput.class)
                             .with(p -> p.setBudgetId(1L))
                             .with(p -> p.setName("Payee Name"))
                             .get();
    }

    private Payee getMockResoultCreateDTO() {
        return GenericBuilder.build(Payee.class)
                             .with(p -> p.setId(1L))
                             .with(p -> p.setName("Payee Name"))
                             .with(p -> p.setBudget(new Budget()))
                             .with(p -> p.getBudget().setId(2L))
                             .with(p -> p.setCreationDate(LocalDateTime.now()))
                             .with(p -> p.setLastUpdate(LocalDateTime.now()))
                             .with(p -> p.setDisabled(false))
                             .get();
    }
}
