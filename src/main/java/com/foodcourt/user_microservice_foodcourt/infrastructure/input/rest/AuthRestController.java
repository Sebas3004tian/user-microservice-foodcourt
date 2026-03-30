package com.foodcourt.user_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.user_microservice_foodcourt.application.handler.IAuthHandler;
import com.foodcourt.user_microservice_foodcourt.domain.model.AuthResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final IAuthHandler authHandler;

    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Authenticates a user with email and password to return a Bearer JWT token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated. Token returned."),
            @ApiResponse(responseCode = "401", description = "Invalid credentials provided"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authHandler.login(request));
    }
}

