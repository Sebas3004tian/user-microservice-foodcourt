package com.foodcourt.user_microservice_foodcourt.infrastructure.configuration;

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


    private static final String ADMIN_ROLE = "ADMIN";
    private static final String OWNER_ROLE = "PROPIETARIO";
    private static final String EMPLOYEE_ROLE = "EMPLEADO";
    private static final String CLIENT_ROLE = "CLIENTE";



    @Bean
    @Order(1)
    CommandLineRunner initRoles(IRoleRepository roleRepository) {
        return args -> {


            boolean adminRoleExists = roleRepository.findOneByName(ADMIN_ROLE).isPresent();
            if(!adminRoleExists){
                roleRepository.save(new RoleEntity(ADMIN_ROLE));
            }
            boolean ownerRoleExists = roleRepository.findOneByName(OWNER_ROLE).isPresent();
            if(!ownerRoleExists){
                roleRepository.save(new RoleEntity(OWNER_ROLE));
            }
            boolean employeeRoleExists = roleRepository.findOneByName(EMPLOYEE_ROLE).isPresent();
            if(!employeeRoleExists){
                roleRepository.save(new RoleEntity(EMPLOYEE_ROLE));
            }
            boolean clientRoleExists = roleRepository.findOneByName(CLIENT_ROLE).isPresent();
            if(!clientRoleExists){
                roleRepository.save(new RoleEntity(CLIENT_ROLE));
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
                RoleEntity adminRole = roleRepository.findOneByName("ADMIN")
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
