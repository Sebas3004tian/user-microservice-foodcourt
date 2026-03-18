package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository;

import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
}
