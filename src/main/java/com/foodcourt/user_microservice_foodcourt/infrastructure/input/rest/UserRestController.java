package com.foodcourt.user_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateClientRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.response.CreateUserResponseDto;
import com.foodcourt.user_microservice_foodcourt.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIO', 'EMPLEADO')")
    @GetMapping("/{id}/phone")
    @Operation(summary = "Get the phone number of some user with the user Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The server has responded to the user's phone number."),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "409", description = "User not found")
    })
    public ResponseEntity<String> getUserNumberPhone(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.getUserNumberPhone(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIO')")
    @GetMapping("/{id}/role")
    @Operation(summary = "Get the role of some user with the user Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The server has responded to the user's role."),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "409", description = "User not found")
    })
    public ResponseEntity<String> getUserRole(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.getUserRoleById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/owner")
    @Operation(summary = "Create an owner user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<CreateUserResponseDto> createOwner(@Valid @RequestBody CreateOwnerRequestDto ownerRequestDto){
        CreateUserResponseDto userResponseDto =  userHandler.createOwner(ownerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PROPIETARIO')")
    @PostMapping("/employee")
    @Operation(summary = "Create an employee user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<CreateUserResponseDto> createEmployee(@Valid @RequestBody CreateEmployeeRequestDto employeeRequestDto) {
        CreateUserResponseDto userResponseDto = userHandler.createEmployee(employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/client")
    @Operation(summary = "Create an client user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<CreateUserResponseDto> createClient(@Valid @RequestBody CreateClientRequestDto createClientRequestDto){
        CreateUserResponseDto userResponseDto =  userHandler.createClient(createClientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }
}
