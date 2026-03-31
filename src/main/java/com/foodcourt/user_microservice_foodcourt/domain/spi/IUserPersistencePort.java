package com.foodcourt.user_microservice_foodcourt.domain.spi;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    Optional<String> findUserNumberPhoneById(Long id);
    Optional<String> findUserRoleById(Long id);
    Optional<User> findOneById(Long id);
    Optional<User> findOneByIdentificationNumber(Long identificationNumber);
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByPhoneNumber(String phoneNumber);
    User createUser(User user);
}
