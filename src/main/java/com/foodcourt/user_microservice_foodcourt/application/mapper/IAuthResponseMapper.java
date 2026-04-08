package com.foodcourt.user_microservice_foodcourt.application.mapper;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginResponseDto;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAuthResponseMapper {
    LoginResponseDto toResponse(LoginResponse loginResponse);
}
