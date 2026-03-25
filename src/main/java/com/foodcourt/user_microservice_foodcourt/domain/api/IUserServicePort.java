package com.foodcourt.user_microservice_foodcourt.domain.api;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;

public interface IUserServicePort {
    String getUserRoleById(Long id);
    void createOwner(User user);
    void createEmployee(User user);
    void createClient(User user);
}
