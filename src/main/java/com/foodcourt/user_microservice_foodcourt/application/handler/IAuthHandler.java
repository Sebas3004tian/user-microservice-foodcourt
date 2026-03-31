package com.foodcourt.user_microservice_foodcourt.application.handler;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginResponseDto;

public interface IAuthHandler {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
