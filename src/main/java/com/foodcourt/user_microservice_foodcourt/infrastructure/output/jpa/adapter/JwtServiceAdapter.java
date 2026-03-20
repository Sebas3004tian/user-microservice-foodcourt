package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.security.TokenUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtServiceAdapter implements IJwtServicePort {

    private final TokenUtils tokenUtils;

    @Override
    public String generateToken(User user) {
        return tokenUtils.createToken(user.getName(), user.getEmail());
    }
}
