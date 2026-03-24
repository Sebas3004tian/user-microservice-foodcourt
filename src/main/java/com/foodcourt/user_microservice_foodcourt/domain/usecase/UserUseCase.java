package com.foodcourt.user_microservice_foodcourt.domain.usecase;

import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.model.UserRole;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserAlreadyExistsException;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort){
        this.userPersistencePort=userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void createOwner(User user) {

        String encryptedPassword = passwordEncoderPort.encode(user.getPassword());

        user.setPassword(encryptedPassword);
        user.setRole(UserRole.PROPIETARIO);
        user.validateAdult();

        if (userPersistencePort.findOneById(user.getId()).isPresent()) {
            throw new UserAlreadyExistsException("User ID already exists");
        }

        if (userPersistencePort.findOneByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User email already exists");
        }

        if (userPersistencePort.findOneByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("User phone number already exists");
        }

        userPersistencePort.createOwner(user);
    }
}
