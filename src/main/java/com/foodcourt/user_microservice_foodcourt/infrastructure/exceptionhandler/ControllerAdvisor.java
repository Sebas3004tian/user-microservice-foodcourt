package com.foodcourt.user_microservice_foodcourt.infrastructure.exceptionhandler;

import com.foodcourt.user_microservice_foodcourt.domain.exception.InvalidCredentialsException;
import com.foodcourt.user_microservice_foodcourt.domain.exception.UnderageUserException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.SecurityConfigurationException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserAlreadyExistsException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";
    private static final String ERROR = "error";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            fieldErrors.put(field, message);
        });

        Map<String, Object> response = new HashMap<>();
        response.put(ERROR, ExceptionResponse.VALIDATION_ERROR.getMessage());
        response.put(MESSAGE, fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> response = Map.of(
                ERROR, ExceptionResponse.USER_NOT_FOUND.getMessage(),
                MESSAGE, ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, String> response = Map.of(
                ERROR, ExceptionResponse.ACCESS_DENIED.getMessage(),
                MESSAGE, ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Map<String, String> response = Map.of(
                ERROR, ExceptionResponse.INVALID_CREDENTIALS.getMessage(),
                MESSAGE, ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(SecurityConfigurationException.class)
    public ResponseEntity<Map<String, String>> handleSecurityConfigurationException(SecurityConfigurationException ex) {
        Map<String, String> response = Map.of(
                ERROR, ExceptionResponse.SECURITY_CONFIGURATION_ERROR.getMessage(),
                MESSAGE, ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

        Map<String, String> response = Map.of(
                ERROR, ExceptionResponse.USER_ALREADY_EXISTS.getMessage(),
                MESSAGE, ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UnderageUserException.class)
    public ResponseEntity<Map<String, String>> handleUnderageUserException(UnderageUserException ex) {
        Map<String, String> response = Map.of(
                ERROR, ExceptionResponse.UNDERAGE_USER.getMessage(),
                MESSAGE, ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}