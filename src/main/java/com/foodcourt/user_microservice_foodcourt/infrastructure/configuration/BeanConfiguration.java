package com.foodcourt.user_microservice_foodcourt.infrastructure.configuration;

import com.foodcourt.user_microservice_foodcourt.domain.api.IAuthServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IRolePersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.usecase.AuthUseCase;
import com.foodcourt.user_microservice_foodcourt.domain.usecase.UserUseCase;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IRoleRepository;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.security.adapter.JwtServiceAdapter;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.repository.IUserRepository;
import com.foodcourt.user_microservice_foodcourt.infrastructure.security.BCryptPasswordEncoderAdapter;
import com.foodcourt.user_microservice_foodcourt.infrastructure.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final TokenUtils tokenUtils;

    @Bean
    public IUserPersistencePort userPersistencePort(){

        return new UserJpaAdapter(userRepository,userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){

        return new RoleJpaAdapter(roleRepository,roleEntityMapper);
    }

    @Bean
    public IJwtServicePort jwtServicePort(){
        return new JwtServiceAdapter(tokenUtils);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(){
        return new BCryptPasswordEncoderAdapter();
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(
                userPersistencePort(),
                rolePersistencePort(),
                passwordEncoderPort()

        );
    }

    @Bean
    public IAuthServicePort authServicePort(){
        return new AuthUseCase(
                userPersistencePort(),
                passwordEncoderPort(),
                jwtServicePort()
        );
    }

}
