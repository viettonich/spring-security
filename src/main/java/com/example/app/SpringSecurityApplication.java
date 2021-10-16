package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.app.entity.User;
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

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUsername("viet");
//		user.setPassword(passwordEncoder.encode("12345"));
//		user.setEmail("viet@gmail.com.vn");
//		userRepository.save(user);
//		System.out.println(user);
	}

}
