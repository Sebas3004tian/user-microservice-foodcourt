package com.foodcourt.user_microservice_foodcourt.domain.usecase;

import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.Role;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.model.UserRole;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IRolePersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.RoleNotFoundException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserAlreadyExistsException;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncoderPort passwordEncoderPort){
        this.userPersistencePort=userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void createOwner(User user) {

        String encryptedPassword = passwordEncoderPort.encode(user.getPassword());

        Role role = rolePersistencePort.findOneByName("PROPIETARIO")
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        user.setPassword(encryptedPassword);
        user.setRole(role);
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
