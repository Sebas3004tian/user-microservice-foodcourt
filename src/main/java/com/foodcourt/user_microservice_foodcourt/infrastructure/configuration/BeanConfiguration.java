package com.foodcourt.user_microservice_foodcourt.infrastructure.configuration;

import com.foodcourt.user_microservice_foodcourt.domain.api.IAuthServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.api.IUserServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.usecase.AuthUseCase;
import com.foodcourt.user_microservice_foodcourt.domain.usecase.UserUseCase;
import com.foodcourt.user_microservice_foodcourt.infrastructure.output.jpa.adapter.JwtServiceAdapter;
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
    private final IUserEntityMapper userEntityMapper;
    private final TokenUtils tokenUtils;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository,userEntityMapper,tokenUtils);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(){
        return new BCryptPasswordEncoderAdapter();
    }

    @Bean
    public IJwtServicePort jwtServicePort(TokenUtils tokenUtils){
        return new JwtServiceAdapter(tokenUtils);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(
                userPersistencePort(),
                passwordEncoderPort()
        );
    }

    @Bean
    public IAuthServicePort authServicePort(){
        return new AuthUseCase(
                userPersistencePort(),
                passwordEncoderPort()
        );
    }

}
