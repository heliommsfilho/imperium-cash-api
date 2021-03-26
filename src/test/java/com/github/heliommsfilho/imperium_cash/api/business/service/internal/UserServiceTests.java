package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.User;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.UserRepository;
import com.github.heliommsfilho.imperium_cash.api.exception.EntityAlreadyRegisteredException;
import com.github.heliommsfilho.imperium_cash.api.helper.GenericBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Tag("Services")
@DisplayName("User Service should")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("create a new User")
    void createNewUser() {
        when(userRepository.save(any())).thenReturn(getMockResponseCreateUser());
        User savedUser = userService.create(getInput());

        Assertions.assertEquals("John Doe", savedUser.getName());
        Assertions.assertEquals("john.doe@company.com", savedUser.getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", savedUser.getPassword());
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals("Some UUID", savedUser.getTenantUUID());
        Assertions.assertNotNull(savedUser.getCreationDate());
        Assertions.assertNotNull(savedUser.getLastUpdate());
    }

    @Test
    @DisplayName(("fail due a already registered email"))
    @Transactional
    void createNewUser_withAlreadyRegisteredEmail__shouldFail() {
        User userOutputMock = GenericBuilder.build(User.class)
                                            .with(u -> u.setId(16L))
                                            .with(u -> u.setName("Another John Doe"))
                                            .with(u -> u.setEmail("john.doe@gmail.com"))
                                            .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                                            .with(u -> u.setTenantUUID("Some UUID"))
                                            .with(u -> u.setCreationDate(LocalDateTime.now()))
                                            .with(u -> u.setLastUpdate(u.getCreationDate()))
                                            .get();

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userOutputMock));
        Exception exception = Assertions.assertThrows(EntityAlreadyRegisteredException.class,
                                                      () -> userService.create(getInput()));

        Assertions.assertEquals("Entity 'User' already registered with identifier 'john.doe@gmail.com'.", exception.getMessage());
    }

    private User getInput() {
        return GenericBuilder.build(User.class)
                             .with(u -> u.setName("John Doe"))
                             .with(u -> u.setEmail("john.doe@gmail.com"))
                             .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                             .get();
    }

    @Test
    @DisplayName("return a User by ID")
    void returnUserById() {
        when(userRepository.findById(any(Long.class))).thenReturn(getMockResponseUser());
        Optional<User> user = userService.getById(1L);

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("John Doe", user.get().getName());
        Assertions.assertEquals("john.doe@company.com", user.get().getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", user.get().getPassword());
        Assertions.assertEquals("63796157-4646-4627-a40f-9718e55d9216", user.get().getTenantUUID());
    }

    @Test
    @DisplayName("return a User by UUID")
    void returnUserByUuid() {
        when(userRepository.findByUUID(any(String.class))).thenReturn(getMockResponseUser());
        Optional<User> user = userService.getByTenantUUID("63796157-4646-4627-a40f-9718e55d9216");

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("John Doe", user.get().getName());
        Assertions.assertEquals("john.doe@company.com", user.get().getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", user.get().getPassword());
        Assertions.assertEquals("63796157-4646-4627-a40f-9718e55d9216", user.get().getTenantUUID());
    }

    private static Optional<User> getMockResponseUser() {
        User user = GenericBuilder.build(User.class)
                                  .with(u -> u.setId(16L))
                                  .with(u -> u.setName("John Doe"))
                                  .with(u -> u.setEmail("john.doe@company.com"))
                                  .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                                  .with(u -> u.setTenantUUID("63796157-4646-4627-a40f-9718e55d9216"))
                                  .get();

        return Optional.ofNullable(user);
    }

    private static User getMockResponseCreateUser() {
        return GenericBuilder.build(User.class)
                             .with(u -> u.setId(16L))
                             .with(u -> u.setName("John Doe"))
                             .with(u -> u.setEmail("john.doe@company.com"))
                             .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                             .with(u -> u.setTenantUUID("Some UUID"))
                             .with(u -> u.setCreationDate(LocalDateTime.now()))
                             .with(u -> u.setLastUpdate(u.getCreationDate()))
                             .get();
    }
}
