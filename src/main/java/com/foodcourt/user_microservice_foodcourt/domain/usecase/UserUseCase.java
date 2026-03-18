package com.foodcourt.user_microservice_foodcourt.domain.usecase;

import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort){
        this.userPersistencePort=userPersistencePort;
    }

    @Override
    public void createOwner(User user) {
        userPersistencePort.createOwner(user);
    }
}
