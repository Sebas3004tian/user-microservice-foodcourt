package com.foodcourt.user_microservice_foodcourt.infrastructure.output.security.adapter;

import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtServiceAdapter implements IJwtServicePort {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity}")
    private Long validity;

    @Override
    public String createToken(Long id, String name, String email, String role) {
        long expirationTime = validity * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name", name);
        extra.put("email", email);
        extra.put("role", role);

        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
