package com.maidoo.maidoo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maidoo.maidoo.domain.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    //Check if the user is already present by userName
    Boolean existsByUsername(String username);
}
