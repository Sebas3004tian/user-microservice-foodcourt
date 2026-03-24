package com.foodcourt.user_microservice_foodcourt.application.handler.impl;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateClientRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateEmployeeRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.application.handler.IUserHandler;
import com.foodcourt.user_microservice_foodcourt.application.mapper.IUserRequestMapper;
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

    @Override
    public void createOwner(CreateOwnerRequestDto ownerRequestDto) {
        User user = userRequestMapper.toOwner(ownerRequestDto);
        userServicePort.createOwner(user);
    }

    @Override
    public void createEmployee(CreateEmployeeRequestDto employeeRequestDto){
        User user = userRequestMapper.toEmployee(employeeRequestDto);
        userServicePort.createEmployee(user);
    }

    @Override
    public void createClient(CreateClientRequestDto createClientRequestDto){
        User user = userRequestMapper.toClient(createClientRequestDto);
        userServicePort.createClient(user);
    }
}
