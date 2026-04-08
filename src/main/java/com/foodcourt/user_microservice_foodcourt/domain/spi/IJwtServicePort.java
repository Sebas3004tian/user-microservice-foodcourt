package com.foodcourt.user_microservice_foodcourt.domain.spi;

import io.jsonwebtoken.Claims;

public interface IJwtServicePort {
    String createToken(Long id, String name, String email, String role);

    Claims extractClaims(String token);
}
