package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.mapper;

import com.foodcourt.user_microservice_foodcourt.domain.model.Role;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRoleEntityMapper {

    RoleEntity toEntity(Role role);
    Role toRole(RoleEntity roleEntity);
}
