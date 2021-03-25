package com.github.heliommsfilho.imperium_cash.api.business.service.system;

import com.github.heliommsfilho.imperium_cash.api.business.service.system.user.UserService;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.User;
import com.github.heliommsfilho.imperium_cash.api.domain.model.system.dto.user.UserCreateDTO;
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

import java.time.LocalDateTime;
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
    @DisplayName("create a new User")
    void createNewUser() {
        when(userRepository.save(any())).thenReturn(getMockResponseCreateUser());
        User savedUser = userService.create(createUserDTO());

        Assertions.assertEquals("Jonh Doe", savedUser.getName());
        Assertions.assertEquals("jonh.doe@company.com", savedUser.getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", savedUser.getPassword());
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals("Some UUID", savedUser.getTenantUUID());
        Assertions.assertNotNull(savedUser.getCreationDate());
        Assertions.assertNotNull(savedUser.getLastUpdate());
    }

    private UserCreateDTO createUserDTO() {
        return GenericBuilder.build(UserCreateDTO.class)
                             .with(u -> u.setName("Jonh Doe"))
                             .with(u -> u.setEmail("jonh.doe@company.com"))
                             .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                             .get();
    }

    @Test
    @DisplayName("return a User by ID")
    void returnUserById() {
        when(userRepository.findById(any(Long.class))).thenReturn(getMockResponseUser());
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
        when(userRepository.findByTenantUUID(any(String.class))).thenReturn(getMockResponseUser());
        Optional<User> user = userService.getByTenantUUID("63796157-4646-4627-a40f-9718e55d9216");

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("Hélio Márcio Filho", user.get().getName());
        Assertions.assertEquals("heliommsfilho@gmail.com", user.get().getEmail());
        Assertions.assertEquals("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC", user.get().getPassword());
        Assertions.assertEquals("63796157-4646-4627-a40f-9718e55d9216", user.get().getTenantUUID());
    }

    private static Optional<User> getMockResponseUser() {
        User user = GenericBuilder.build(User.class)
                                  .with(u -> u.setId(16L))
                                  .with(u -> u.setName("Hélio Márcio Filho"))
                                  .with(u -> u.setEmail("heliommsfilho@gmail.com"))
                                  .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                                  .with(u -> u.setTenantUUID("63796157-4646-4627-a40f-9718e55d9216"))
                                  .get();

        return Optional.ofNullable(user);
    }

    private static User getMockResponseCreateUser() {
        return GenericBuilder.build(User.class)
                             .with(u -> u.setId(16L))
                             .with(u -> u.setName("Jonh Doe"))
                             .with(u -> u.setEmail("jonh.doe@company.com"))
                             .with(u -> u.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC"))
                             .with(u -> u.setTenantUUID("Some UUID"))
                             .with(u -> u.setCreationDate(LocalDateTime.now()))
                             .with(u -> u.setLastUpdate(u.getCreationDate()))
                             .get();
    }
}
