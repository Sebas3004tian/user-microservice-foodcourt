package com.foodcourt.user_microservice_foodcourt.application.handler;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginResponseDto;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginResponse;

public interface IAuthHandler {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
