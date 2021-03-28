package com.github.heliommsfilho.imperium_cash.api.domain.repository;

import com.github.heliommsfilho.imperium_cash.api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE LOWER(u.tenantUUID) = LOWER(:uuid)")
    Optional<User> findByUUID(@Param("uuid") String uuid);

    @Query(value = "SELECT u.id FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<Long> findByEmail(@Param("email") String email);
}
