package com.webdev.pixel_vault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webdev.pixel_vault.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String userName);

}
