package com.github.heliommsfilho.imperium_cash.api.business.service.system.user;

import com.github.heliommsfilho.imperium_cash.api.domain.model.system.User;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.systemspace.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByTenantUUID(String tenantUUID) {
        return userRepository.findByTenantUUID(tenantUUID);
    }
}
