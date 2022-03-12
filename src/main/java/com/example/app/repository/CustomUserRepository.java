package com.example.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.entity.User;

@Repository
public interface CustomUserRepository {
    public void insert(List<User> userList);

    public User insert(User user);
}
