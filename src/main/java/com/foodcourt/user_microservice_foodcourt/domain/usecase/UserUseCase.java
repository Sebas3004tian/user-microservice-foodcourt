package com.foodcourt.user_microservice_foodcourt.domain.usecase;

import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.Role;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IRolePersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.RoleNotFoundException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserAlreadyExistsException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserNotFoundException;

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
    public String getUserEmail(Long id) {
        return userPersistencePort.findUserEmailById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist.")
                );
    }

    @Override
    public String getUserNumberPhone(Long id) {
        return userPersistencePort.findUserNumberPhoneById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist.")
                );
    }

    @Override
    public String getUserRoleById(Long id){
        return userPersistencePort.findUserRoleById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " does not exist.")
                );
    }

    @Override
    public User createOwner(User user) {
        user.validateAdult();
        return createUserWithRole(user, "PROPIETARIO");
    }

    @Override
    public User createEmployee(User user) {
        return createUserWithRole(user, "EMPLEADO");
    }

    @Override
    public User createClient(User user){
        return createUserWithRole(user, "CLIENTE");
    }

    private User createUserWithRole(User user, String roleName) {

        String encryptedPassword = passwordEncoderPort.encode(user.getPassword());

        Role role = rolePersistencePort.findOneByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        user.setPassword(encryptedPassword);
        user.setRole(role);

        if (userPersistencePort.findOneByIdentificationNumber(user.getIdentificationNumber()).isPresent()) {
            throw new UserAlreadyExistsException("User identification already exists");
        }

        if (userPersistencePort.findOneByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User email already exists");
        }

        if (userPersistencePort.findOneByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("User phone number already exists");
        }

        return userPersistencePort.createUser(user);
    }
}
