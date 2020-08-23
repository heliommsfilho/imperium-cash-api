package com.github.heliommsfilho.imperium_cash.api.service;

import com.github.heliommsfilho.imperium_cash.api.model.User;
import com.github.heliommsfilho.imperium_cash.api.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Test
    void userGetById_shouldReturnHelioUser() {
        when(userRepository.findById(any(Long.class))).thenReturn(UserServiceTests.getUser());
        Assertions.assertEquals("heliommsfilho@gmail.com", userRepository.findById(1L).get().getEmail());
    }

    @Test
    void userGetByTenantUUID_shouldReturnHelioUser() {
        when(userRepository.findByTenantUUID(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.findByTenantUUID("63796157-4646-4627-a40f-9718e55d9216")).thenReturn(UserServiceTests.getUser());
        Assertions.assertEquals("heliommsfilho@gmail.com", userRepository.findByTenantUUID("63796157-4646-4627-a40f-9718e55d9216").get().getEmail());
        Assertions.assertFalse(userRepository.findByTenantUUID("99999999-9999-9999-9999-999999999999").isPresent());
    }

    private static Optional<User> getUser() {
        User userHelio = new User();
        userHelio.setName("Hélio Márcio Filho");
        userHelio.setEmail("heliommsfilho@gmail.com");
        userHelio.setPassword("$2a$10$l.bU8lPhy0AVi1zffT0AU.tO/yacKs4S7G74YwJzVWk4K0jBAXnAC");
        userHelio.setTenantUUID("63796157-4646-4627-a40f-9718e55d9216");

        return Optional.ofNullable(userHelio);
    }
}
