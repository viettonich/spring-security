package com.example.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.app.entity.Category;
import com.example.app.entity.Product;
import com.example.app.entity.Role;
import com.example.app.entity.User;
import com.example.app.repository.CategoryRepository;
import com.example.app.repository.ProductRepository;
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
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> list = new ArrayList<>();

        User user = new User();
        user.setUsername("viet");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setEmail("viet@gmail.com.vn");

        Role roleAdmin = new Role();
        roleAdmin.setRoleName("Admin");

        List<Role> roleList = new ArrayList<>();
        roleList.add(roleAdmin);

        user.setRoles(roleList);
        list.add(user);
        userRepository.save(user);

        Category category = new Category();
        category.setName("Laptop");
        categoryRepository.save(category);

        Product product = new Product();
        product.setName("Asus");
        product.setCategory(category);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        productRepository.save(product);

        //productRepository.getListProduct().forEach(p -> System.out.println(p.getProductName()));

        //productRepository.getProductListByCategoryId((long) 1).forEach(p -> System.out.println(p.getProductName()));
        
        //categoryRepository.getListCategory().get(0).getProductList().forEach(p -> System.out.println(p.getName()));

    }

}
