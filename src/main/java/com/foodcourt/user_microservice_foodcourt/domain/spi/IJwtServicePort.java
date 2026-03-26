package com.foodcourt.user_microservice_foodcourt.domain.spi;

public interface IJwtServicePort {
    String generateToken(Long id, String name, String email, String role);
}
