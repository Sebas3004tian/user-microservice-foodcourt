package com.foodcourt.user_microservice_foodcourt.infrastructure.input.rest;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.handler.IUserHandler;
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

    @PostMapping("/")
    public ResponseEntity<Void> createOwner(@RequestBody CreateOwnerRequestDto ownerRequestDto){
        userHandler.createOwner(ownerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
