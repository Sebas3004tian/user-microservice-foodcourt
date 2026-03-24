package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserAlreadyExistsException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;

    private final IUserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findOneByEmail(String email) {
        Optional<UserEntity> entityOpt = userRepository.findOneByEmail(email);

        if (entityOpt.isPresent()) {
            User user = userEntityMapper.toUser(entityOpt.get());
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public User createOwner(User user) {

        if (userRepository.findById(user.getId()).isPresent()){
            throw new UserAlreadyExistsException("User ID already exists");
        }
        if (userRepository.findOneByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("User email already exist");
        }
        if (userRepository.findOneByPhoneNumber(user.getPhoneNumber()).isPresent()){
            throw new UserAlreadyExistsException("User phone number already exist");
        }
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toUser(userEntity);
    }
}
