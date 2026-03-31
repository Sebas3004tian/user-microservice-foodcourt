package com.foodcourt.user_microservice_foodcourt.domain.api;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;

public interface IUserServicePort {
    String getUserNumberPhone(Long id);
    String getUserRoleById(Long id);
    User createOwner(User user);
    User createEmployee(User user);
    User createClient(User user);
}
