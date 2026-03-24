package com.foodcourt.user_microservice_foodcourt.domain.api;

import com.foodcourt.user_microservice_foodcourt.domain.model.Role;

public interface IRoleServicePort {
    void createRole(Role role);
}
