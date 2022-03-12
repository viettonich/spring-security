package com.example.app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.app.entity.Role;
import com.example.app.entity.User;
import com.example.app.repository.CustomUserRepository;

@Repository
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private static final int batchSize = 5;
    private static final int entityCount = 50;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void insert(List<User> userList) {
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);

        try {
            for (int i = 0; i < entityCount; i++) {

                User user = new User();
                user.setUsername("viet");
                user.setPassword(passwordEncoder.encode("12345"));
                user.setEmail("viet@gmail.com.vn");

                Role roleAdmin = new Role();
                roleAdmin.setRoleName("Admin");

                Role roleUser = new Role();
                roleUser.setRoleName("User");

                List<Role> roleList = new ArrayList<>();
                roleList.add(roleUser);
                roleList.add(roleAdmin);

                user.setRoles(roleList);

                entityManager.persist(user);
                if (i % 5 == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            // entityManager.flush();

        } catch (RuntimeException e) {
            throw e;
        } finally {
            entityManager.unwrap(Session.class).setJdbcBatchSize(null);
        }
    }

    @Override
    public User insert(User user) {
        entityManager.persist(user);
        return user;
    }

}
