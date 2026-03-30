package com.foodcourt.user_microservice_foodcourt.infrastructure.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
