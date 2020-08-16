package com.github.heliommsfilho.imperium_cash.api.repository.user;

import com.github.heliommsfilho.imperium_cash.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTenantUUID(String tenantUUID);
}
