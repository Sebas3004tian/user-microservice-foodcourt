package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter;

import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
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
    public Optional<String> getUserRoleById(Long id) {
        return userRepository.findRoleByUserId(id);
    }

    @Override
    public Optional<User> findOneById(Long id){
        return userRepository.findById(id)
                .map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> findOneByIdentificationNumber(Long identificationNumber) {
        return userRepository.findOneByIdentificationNumber(identificationNumber)
                .map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email)
                .map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> findOneByPhoneNumber(String phoneNumber) {
        return userRepository.findOneByPhoneNumber(phoneNumber)
                .map(userEntityMapper::toUser);
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
        return userEntityMapper.toUser(userEntity);
    }
}
