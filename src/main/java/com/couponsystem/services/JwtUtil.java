package com.couponsystem.services;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.couponsystem.entities.UserDetails;
import com.couponsystem.services.ClientService.ClientType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
@Scope("prototype")
public class JwtUtil {

	public UserDetails userDetails;

	public Instant now = Instant.now();

	private String algorithm = SignatureAlgorithm.HS256.getJcaName();
	private byte[] secretKeyEncoded = "this+is+the+secret+key+and+only+i+know+it+dont+tell+no+one".getBytes();
	private byte[] secretKeyDecoded = Base64.getDecoder().decode(secretKeyEncoded);

	private Key key = new SecretKeySpec(secretKeyDecoded, algorithm);

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userDetails.id);
		claims.put("userName", userDetails.name);
		claims.put("userType", userDetails.userType);
		return createToken(claims, userDetails.email);
	}

	private String createToken(Map<String, Object> claims, String email) {
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(10, ChronoUnit.HOURS))).signWith(key).compact();
	}

	public Claims extractAllClaims(String token) {
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
		return jwtParser.parseClaimsJws(token).getBody();
	}

	public String extractEmail(String token) {
		return extractAllClaims(token).getSubject();
	}

	public int extractId(String token) {
		return (int) extractAllClaims(token).get("userId");
	}

	public ClientType extractUserType(String token) {
		Object obj = extractAllClaims(token).get("userType");
		String temp = obj.toString();
		ClientType type = ClientType.valueOf(temp);
		System.out.println(type);
		return type;
	}

	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	private boolean isTokenExpired(String token) {
		try {
			extractAllClaims(token);
			return false;
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userEmail = extractEmail(token);
		return (userEmail.equals(userDetails.email) && !isTokenExpired(token));
	}

}
