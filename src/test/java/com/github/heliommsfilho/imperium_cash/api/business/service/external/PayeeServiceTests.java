package com.github.heliommsfilho.imperium_cash.api.business.service.external;

import com.github.heliommsfilho.imperium_cash.api.domain.api.input.PayeeInput;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Budget;
import com.github.heliommsfilho.imperium_cash.api.domain.model.Payee;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.PayeeRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityNotRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("Payee Service should")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PayeeServiceTests {

    @Mock
    private PayeeRepository payeeRepository;

    @Mock
    private BudgetServicie budgetServicie;

    @Mock
    private PayeeService payeeService;

    @Test
    @DisplayName("create a new Payee")
    void createNewPayee__shouldSucceed() {
        when(payeeService.getPayeeRepository()).thenReturn(this.payeeRepository);
        when(payeeRepository.save(any())).thenReturn(getMockResultInput());
        when(payeeService.create(any())).thenCallRealMethod();

        /* Bypass Budget validation to mock a valid Budget */
        doNothing().when(payeeService).validate(any());

        Payee payeeInput = EntityDTOHelper.getInstance().map(getInput(), Payee.class);
        Payee savedPayee = payeeService.create(payeeInput);

        Assertions.assertEquals(1L, savedPayee.getId());
        Assertions.assertEquals(2L, savedPayee.getBudget().getId());
        Assertions.assertEquals("Payee Name", savedPayee.getName());
    }

    @Test
    @DisplayName("fail due non existent Budget")
    void createNewPayee_withNonExistentBudget__shouldFail() {
        /* Mock a non existent Budget for given ID being returned from Database */
        when(budgetServicie.getBudget(any())).thenReturn(Optional.empty());
        when(payeeService.getBudgetServicie()).thenReturn(this.budgetServicie);
        when(payeeService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(payeeService).validate(any());

        Payee payeeInput = EntityDTOHelper.getInstance().map(getInput(), Payee.class);
        Exception exception = Assertions.assertThrows(EntityNotRegisteredException.class,
                                                      () -> payeeService.create(payeeInput));

        Assertions.assertEquals("Entity 'Payee' not registered with identifier '2'.", exception.getMessage());
    }

    @Test
    @DisplayName("fail due already registered Payee name")
    void createNewPayee_withAlreadyRegisteredName__shouldFail() {
        Budget mockFoundBudget = GenericBuilder.build(Budget.class).with(b -> b.setId(2L)).get();
        Payee mockFoundPayee = GenericBuilder.build(Payee.class).with(p -> p.setName("Payee Name")).get();

        when(payeeRepository.getByName(anyString())).thenReturn(Optional.of(mockFoundPayee));
        when(budgetServicie.getBudget(any())).thenReturn(Optional.of(mockFoundBudget));
        when(payeeService.getBudgetServicie()).thenReturn(this.budgetServicie);
        when(payeeService.getPayeeRepository()).thenReturn(this.payeeRepository);
        when(payeeService.create(any())).thenCallRealMethod();
        doCallRealMethod().when(payeeService).validate(any());

        Payee payeeInput = EntityDTOHelper.getInstance().map(getInput(), Payee.class);
        Exception exception = Assertions.assertThrows(EntityAlreadyRegisteredException.class,
                                                      () -> payeeService.create(payeeInput));

        Assertions.assertEquals("Entity 'Payee' already registered with identifier 'Payee Name'.", exception.getMessage());
    }

    private PayeeInput getInput() {
        return GenericBuilder.build(PayeeInput.class)
                             .with(p -> p.setBudgetId(2L))
                             .with(p -> p.setName("Payee Name"))
                             .get();
    }

    private Payee getMockResultInput() {
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
