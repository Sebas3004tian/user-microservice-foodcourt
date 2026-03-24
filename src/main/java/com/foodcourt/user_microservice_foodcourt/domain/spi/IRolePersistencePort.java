package com.foodcourt.user_microservice_foodcourt.domain.spi;

import com.foodcourt.user_microservice_foodcourt.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> findOneById(Long id);
    Optional<Role> findOneByName(String name);
    Role createRole(Role role);
}
