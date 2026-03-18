package com.foodcourt.user_microservice_foodcourt.domain.spi;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;

public interface IUserPersistencePort {
    User createOwner(User user);

}
