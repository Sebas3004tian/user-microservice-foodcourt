package com.foodcourt.user_microservice_foodcourt.domain.usecase;

import com.foodcourt.user_microservice_foodcourt.domain.exception.InvalidCredentialsException;
import com.foodcourt.user_microservice_foodcourt.domain.api.IAuthServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginResponse;
import com.foodcourt.user_microservice_foodcourt.domain.model.LoginRequest;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;


public class AuthUseCase implements IAuthServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IJwtServicePort jwtServicePort;

    public AuthUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, IJwtServicePort jwtServicePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.jwtServicePort = jwtServicePort;
    }


    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userPersistencePort.findOneByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found, check credentials"));

        if (!passwordEncoderPort.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtServicePort.createToken(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getName()
        );

        return new LoginResponse(token);
    }
}
