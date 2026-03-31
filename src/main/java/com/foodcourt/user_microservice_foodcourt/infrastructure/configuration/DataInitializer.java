package com.foodcourt.user_microservice_foodcourt.infrastructure.configuration;

import com.foodcourt.user_microservice_foodcourt.domain.model.UserRole;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.RoleNotFoundException;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.RoleEntity;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IRoleRepository;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    @Order(1)
    CommandLineRunner initRoles(IRoleRepository roleRepository) {
        return args -> {

            boolean adminRoleExists = roleRepository.findOneByName(UserRole.ADMIN.name()).isPresent();
            if(!adminRoleExists){
                roleRepository.save(new RoleEntity(UserRole.ADMIN.name()));
            }
            boolean ownerRoleExists = roleRepository.findOneByName(UserRole.PROPIETARIO.name()).isPresent();
            if(!ownerRoleExists){
                roleRepository.save(new RoleEntity(UserRole.PROPIETARIO.name()));
            }
            boolean employeeRoleExists = roleRepository.findOneByName(UserRole.EMPLEADO.name()).isPresent();
            if(!employeeRoleExists){
                roleRepository.save(new RoleEntity(UserRole.EMPLEADO.name()));
            }
            boolean clientRoleExists = roleRepository.findOneByName(UserRole.CLIENTE.name()).isPresent();
            if(!clientRoleExists){
                roleRepository.save(new RoleEntity(UserRole.CLIENTE.name()));
            }
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner initAdminUser(
            IUserRepository userRepository,
            IRoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            String adminEmail = "admin@admin.com";

            boolean exists = userRepository.findOneByEmail(adminEmail).isPresent();

            if (!exists) {
                RoleEntity adminRole = roleRepository.findOneByName(UserRole.ADMIN.name())
                        .orElseThrow(() -> new RoleNotFoundException("Role ADMIN not found"));

                UserEntity admin = new UserEntity();
                admin.setName("Admin");
                admin.setLastName("Admin");
                admin.setIdentificationNumber(1L);
                admin.setPhoneNumber("+1");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole(adminRole);

                userRepository.save(admin);
            }
        };
    }
}
