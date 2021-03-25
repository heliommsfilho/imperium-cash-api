package com.github.heliommsfilho.imperium_cash.api.business.service.internal;

import com.github.heliommsfilho.imperium_cash.api.domain.model.User;
import com.github.heliommsfilho.imperium_cash.api.domain.api.input.UserInput;
import com.github.heliommsfilho.imperium_cash.api.domain.repository.UserRepository;
import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(UserInput input) {
        User user = EntityDTOHelper.getInstance().map(input, User.class);
        user.setTenantUUID(UUID.randomUUID().toString());

        return userRepository.save(user);
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByTenantUUID(String tenantUUID) {
        return userRepository.findByTenantUUID(tenantUUID);
    }
}
