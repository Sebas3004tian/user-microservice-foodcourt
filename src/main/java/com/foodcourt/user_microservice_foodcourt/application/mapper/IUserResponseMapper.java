package com.foodcourt.user_microservice_foodcourt.application.mapper;

import com.foodcourt.user_microservice_foodcourt.application.dto.response.CreateUserResponseDto;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    @Mapping(source = "id", target = "userId")
    CreateUserResponseDto toResponse(User user);

}
