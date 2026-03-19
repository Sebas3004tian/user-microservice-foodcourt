package com.foodcourt.user_microservice_foodcourt.domain.exception;


public class UnderageUserException extends RuntimeException {
    public UnderageUserException() {
        super("User is not of legal age");
    }
}
