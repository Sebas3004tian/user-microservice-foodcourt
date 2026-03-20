package com.foodcourt.user_microservice_foodcourt.application.mapper;

import com.foodcourt.user_microservice_foodcourt.application.dto.request.CreateOwnerRequestDto;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    User toOwner(CreateOwnerRequestDto ownerRequestDto);
}
