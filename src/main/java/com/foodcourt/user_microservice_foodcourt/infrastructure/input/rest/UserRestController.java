package com.foodcourt.user_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @PostMapping("/owner")
    @Operation(summary = "Create an owner user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<Void> createOwner(@Valid @RequestBody CreateOwnerRequestDto ownerRequestDto){
        userHandler.createOwner(ownerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
