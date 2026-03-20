package com.foodcourt.user_microservice_foodcourt.application.handler;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;

public interface IUserHandler {
    void createOwner(CreateOwnerRequestDto ownerRequestDto);
}
