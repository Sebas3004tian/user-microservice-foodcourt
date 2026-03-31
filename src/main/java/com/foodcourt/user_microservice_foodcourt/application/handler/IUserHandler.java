package com.foodcourt.user_microservice_foodcourt.application.handler;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateClientRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.response.CreateUserResponseDto;

public interface IUserHandler {
    CreateUserResponseDto createOwner(CreateOwnerRequestDto ownerRequestDto);
    CreateUserResponseDto createEmployee(CreateEmployeeRequestDto employeeRequestDto);
    CreateUserResponseDto createClient(CreateClientRequestDto createClientRequestDto);
    String getUserRoleById(Long id);
    String getUserNumberPhone(Long id);
}
