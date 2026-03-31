package com.foodcourt.user_microservice_foodcourt.application.mapper;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.LoginRequestDto;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IAuthRequestMapper {
    LoginRequest toLoginRequest(LoginRequestDto loginRequestDto);
}
