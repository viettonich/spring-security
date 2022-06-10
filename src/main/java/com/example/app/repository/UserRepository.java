package com.example.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT U FROM User U LEFT JOIN FETCH U.roles WHERE U.id = ?1 ")
    Optional<User> findById(Long userId);
}
