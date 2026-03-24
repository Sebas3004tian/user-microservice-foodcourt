package com.foodcourt.user_microservice_foodcourt.infrastructure.exceptionhandler;

public enum ExceptionResponse {

    VALIDATION_ERROR("Validation error"),
    ACCESS_DENIED("You do not have permission to access this resource"),
    INVALID_CREDENTIALS("Invalid credentials"),
    SECURITY_CONFIGURATION_ERROR("Error configuring security"),
    USER_ALREADY_EXISTS("User already exists"),
    UNDERAGE_USER("User must be of legal age");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}