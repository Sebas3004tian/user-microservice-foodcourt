package com.foodcourt.user_microservice_foodcourt.infrastructure.exception;

public class SecurityConfigurationException extends RuntimeException {
    public SecurityConfigurationException(String message) {
        super(message);
    }
}
