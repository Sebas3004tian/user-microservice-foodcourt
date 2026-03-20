package com.foodcourt.user_microservice_foodcourt.application.handler.impl;

import com.foodcourt.user_microservice_foodcourt.application.handler.IAuthHandler;
import com.foodcourt.user_microservice_foodcourt.domain.api.IAuthServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.AuthResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {
    private final IAuthServicePort authServicePort;

    @Override
    public AuthResponse login(LoginRequest request) {
        return authServicePort.login(request);
    }
}
