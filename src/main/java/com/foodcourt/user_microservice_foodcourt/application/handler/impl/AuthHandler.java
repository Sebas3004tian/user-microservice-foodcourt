package com.foodcourt.user_microservice_foodcourt.application.handler.impl;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginResponseDto;
import com.foodcourt.user_microservice_foodcourt.application.handler.IAuthHandler;
import com.foodcourt.user_microservice_foodcourt.application.mapper.IAuthRequestMapper;
import com.foodcourt.user_microservice_foodcourt.application.mapper.IAuthResponseMapper;
import com.foodcourt.user_microservice_foodcourt.domain.api.IAuthServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandler implements IAuthHandler {
    private final IAuthServicePort authServicePort;
    private final IAuthRequestMapper loginRequestMapper;
    private final IAuthResponseMapper loginResponseMapper;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginRequest loginRequest = loginRequestMapper.toLoginRequest(loginRequestDto);
        return loginResponseMapper.toResponse(authServicePort.login(loginRequest));
    }
}