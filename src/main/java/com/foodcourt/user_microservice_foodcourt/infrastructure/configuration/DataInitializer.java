package com.foodcourt.user_microservice_foodcourt.infrastructure.configuration;

import com.foodcourt.user_microservice_foodcourt.domain.model.UserRole;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.entity.UserEntity;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdminUser(
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            String adminEmail = "admin@admin.com";

            boolean exists = userRepository.findOneByEmail(adminEmail).isPresent();

            if (!exists) {
                UserEntity admin = new UserEntity();
                admin.setId(1L);
                admin.setName("Admin");
                admin.setLastName("Admin");
                admin.setPhoneNumber("+1");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole(UserRole.ADMIN);

                userRepository.save(admin);

            }
        };
    }
}
