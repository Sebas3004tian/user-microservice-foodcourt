package com.foodcourt.user_microservice_foodcourt.application.handler;

import com.foodcourt.user_microservice_foodcourt.domain.model.AuthResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;

public interface IAuthHandler {
    AuthResponse login(LoginRequest request);
}
