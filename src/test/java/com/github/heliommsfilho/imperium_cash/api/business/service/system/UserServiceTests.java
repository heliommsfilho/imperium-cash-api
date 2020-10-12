package com.github.heliommsfilho.imperium_cash.api.business.service.system;

import com.github.heliommsfilho.imperium_cash.api.business.service.system.user.UserService;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.User;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.user.UserRepository;
import com.github.heliommsfilho.imperium_cash.api.infraestructure.helper.GenericBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("User Service should")
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("return a User by ID")
    void returnUserById() {
        when(userRepository.findById(any(Long.class))).thenReturn(createUser());
        Optional<User> user = userService.getById(1L);

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("Hélio Márcio Filho", user.get().getName());
        Assertions.assertEquals("heliommsfilho@gmail.com", user.get().getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", user.get().getPassword());
        Assertions.assertEquals("63796157-4646-4627-a40f-9718e55d9216", user.get().getTenantUUID());
    }

    @Test
    @DisplayName("return a User by UUID")
    void returnUserByUuid() {
        when(userRepository.findByTenantUUID(any(String.class))).thenReturn(createUser());
        Optional<User> user = userService.getByTenantUUID("63796157-4646-4627-a40f-9718e55d9216");

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("Hélio Márcio Filho", user.get().getName());
        Assertions.assertEquals("heliommsfilho@gmail.com", user.get().getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", user.get().getPassword());
        Assertions.assertEquals("63796157-4646-4627-a40f-9718e55d9216", user.get().getTenantUUID());
    }

    private static Optional<User> createUser() {
        User user = GenericBuilder.build(User.class)
                                  .with(u -> u.setName("Hélio Márcio Filho"))
                                  .with(u -> u.setEmail("heliommsfilho@gmail.com"))
                                  .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                                  .with(u -> u.setTenantUUID("63796157-4646-4627-a40f-9718e55d9216"))
                                  .get();

        return Optional.ofNullable(user);
    }
}
