package com.foodcourt.user_microservice_foodcourt.domain.api;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;

public interface IUserServicePort {
    void createOwner(User user);
    void createEmployee(User user);
}
