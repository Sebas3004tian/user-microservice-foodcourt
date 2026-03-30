package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter;

import com.foodcourt.user_microservice_foodcourt.domain.model.Role;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IRolePersistencePort;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.RoleEntity;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findOneById(Long id) {
        return roleRepository.findById(id)
                .map(roleEntityMapper::toRole);
    }

    @Override
    public Optional<Role> findOneByName(String name) {
        return roleRepository.findOneByName(name)
                .map(roleEntityMapper::toRole);
    }

    @Override
    public Role createRole(Role role) {
        RoleEntity roleEntity = roleRepository.save(roleEntityMapper.toEntity(role));
        return roleEntityMapper.toRole(roleEntity);
    }
}
