package com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository;

import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u.phoneNumber FROM UserEntity u WHERE u.id = :id")
    Optional<String> findPhoneNumberById(Long id);

    @Query("""
    SELECT r.name
    FROM UserEntity u
    JOIN u.role r
    WHERE u.id = :id
    """)
    Optional<String> findRoleByUserId(Long id);
    Optional<UserEntity> findOneByIdentificationNumber(Long email);
    Optional<UserEntity> findOneByEmail(String email);
    Optional<UserEntity> findOneByPhoneNumber(String phoneNumber);
}
