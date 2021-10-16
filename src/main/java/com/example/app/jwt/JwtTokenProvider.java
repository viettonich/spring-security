package com.example.app.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.app.dto.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private final String JWT_SECRET = "111111";

	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 604800000L;

	// Tạo ra jwt từ thông tin user
	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		return Jwts.builder().setSubject(Long.toString(userDetails.getUser().getId())).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	// Lấy thông tin user từ jwt
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			System.err.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.err.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.err.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.err.println("JWT claims string is empty.");
		}
		return false;
	}
}
