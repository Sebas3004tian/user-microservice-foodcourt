package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter;

import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.security.TokenUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtServiceAdapter implements IJwtServicePort {

    private final TokenUtils tokenUtils;

    @Override
    public String generateToken(String name, String email, String role) {
        return tokenUtils.createToken(name, email, role);
    }
}
