package com.foodcourt.user_microservice_foodcourt.domain.spi;

import com.foodcourt.user_microservice_foodcourt.domain.model.AuthResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {
    Optional<User> findOneByEmail(String email);
    User createOwner(User user);
    AuthResponse login(LoginRequest request);
}
