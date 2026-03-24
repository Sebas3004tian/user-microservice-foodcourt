package com.foodcourt.user_microservice_foodcourt.domain.spi;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    Optional<User> findOneById(Long id);
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByPhoneNumber(String phoneNumber);
    User createUser(User user);
}
