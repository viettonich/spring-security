package com.example.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.app.entity.Role;
import com.example.app.entity.User;
import com.example.app.repository.CustomUserRepository;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    CustomUserRepository customUserRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        long time1 = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setUsername("viet");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setEmail("viet@gmail.com.vn");

            Role roleAdmin = new Role();
            roleAdmin.setRoleName("Admin");

            //Role role = roleRepository.save(roleAdmin);

            List<Role> roleList = new ArrayList<>();
            roleList.add(roleAdmin);

            user.setRoles(roleList);

            list.add(user);
            
            userRepository.save(user);
            
            //customUserRepository.insert(user);
        }
        //customUserRepository.insert(list);
        long time2 = System.currentTimeMillis();
        
        System.out.println(time2 - time1);

    }

}
