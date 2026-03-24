package com.foodcourt.user_microservice_foodcourt.domain.spi;

public interface IJwtServicePort {
    String generateToken(String name, String email, String role);
}
