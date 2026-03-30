package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository;

import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findOneByName(String name);
}
