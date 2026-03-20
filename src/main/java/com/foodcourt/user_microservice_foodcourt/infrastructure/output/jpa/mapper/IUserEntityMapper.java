package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.mapper;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    User toUser(UserEntity userEntity);
}
