package com.foodcourt.user_microservice_foodcourt.application.handler.impl;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateClientRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.response.CreateUserResponseDto;
import com.foodcourt.user_microservice_foodcourt.application.handler.IUserHandler;
import com.foodcourt.user_microservice_foodcourt.application.mapper.IUserRequestMapper;
import com.foodcourt.user_microservice_foodcourt.application.mapper.IUserResponseMapper;
import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public String getUserRoleById(Long id){
        return userServicePort.getUserRoleById(id);
    }

    @Override
    public String getUserNumberPhone(Long id) {
        return userServicePort.getUserNumberPhone(id);
    }

    @Override
    public String getUserEmail(Long id) {
        return userServicePort.getUserEmail(id);
    }

    @Override
    public CreateUserResponseDto createOwner(CreateOwnerRequestDto ownerRequestDto) {
        User user = userRequestMapper.toOwner(ownerRequestDto);
        return userResponseMapper.toResponse( userServicePort.createOwner(user));
    }

    @Override
    public CreateUserResponseDto createEmployee(CreateEmployeeRequestDto employeeRequestDto){
        User user = userRequestMapper.toEmployee(employeeRequestDto);
        return userResponseMapper.toResponse(userServicePort.createEmployee(user));
    }

    @Override
    public CreateUserResponseDto createClient(CreateClientRequestDto createClientRequestDto){
        User user = userRequestMapper.toClient(createClientRequestDto);
        return userResponseMapper.toResponse(userServicePort.createClient(user));
    }

}
