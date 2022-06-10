package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.CustomUserDetails;
import com.example.app.jwt.JwtTokenProvider;
import com.example.app.ui.request.LoginRequest;
import com.example.app.ui.response.LoginResponse;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

		// Xác thực từ username và password.
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		return new LoginResponse(jwt);
	}

	@GetMapping("/random1")
	public String randomStuff() {
		//throw new CustomException("12313", HttpStatus.CONFLICT);
		return "JWT Hợp lệ mới có thể thấy được message này";
	}

}
