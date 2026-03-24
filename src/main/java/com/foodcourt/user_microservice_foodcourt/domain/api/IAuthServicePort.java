package com.foodcourt.user_microservice_foodcourt.domain.api;


import com.foodcourt.user_microservice_foodcourt.domain.model.AuthResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;

public interface IAuthServicePort {
    AuthResponse login(LoginRequest request);
}