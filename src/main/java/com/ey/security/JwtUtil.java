package com.ey.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

		private final String jwtsecret="Thisissecretkeythatisatleast32charslong";
		private final long jwtexpier=8640000;

		private SecretKey getSigningkey() {
			byte[] keyBytes=jwtsecret.getBytes(StandardCharsets.UTF_8);
			return Keys.hmacShaKeyFor(keyBytes);
		}
		
	    public String generateToken(String subject,
	                                String role) {

	        return Jwts.builder()
	                .setSubject(subject)
	                .claim("role", role)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + jwtexpier))
	                .signWith(getSigningkey(),SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public Claims extractClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningkey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
	    public String extractSubject(String token) {
	    	return Jwts.parserBuilder()
	    			.setSigningKey(getSigningkey())
	    			.build()
	    			.parseClaimsJws(token.substring(7))
	    			.getBody()
	    			.getSubject();
	    }
	    public boolean validateToken(String token) {
			try {
				Jwts.parserBuilder()
					.setSigningKey(getSigningkey())
					.build()
					.parseClaimsJws(token);
				return true;
			}
			catch(JwtException | IllegalArgumentException e) {
				return false;
			}
		}
}
