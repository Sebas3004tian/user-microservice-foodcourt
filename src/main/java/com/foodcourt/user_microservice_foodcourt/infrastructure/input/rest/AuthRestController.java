package com.foodcourt.user_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.user_microservice_foodcourt.application.handler.IAuthHandler;
import com.foodcourt.user_microservice_foodcourt.domain.model.AuthResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
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
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authHandler.login(request));
    }
}

