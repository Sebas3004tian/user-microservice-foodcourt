package com.foodcourt.user_microservice_foodcourt.domain.api;


import com.foodcourt.user_microservice_foodcourt.domain.model.LoginResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;

public interface IAuthServicePort {
    LoginResponse login(LoginRequest request);
}