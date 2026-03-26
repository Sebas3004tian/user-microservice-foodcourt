package com.foodcourt.user_microservice_foodcourt;

import com.foodcourt.user_microservice_foodcourt.domain.model.*;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IJwtServicePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.usecase.AuthUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LoginAuthUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IJwtServicePort jwtServicePort;

    @InjectMocks
    private AuthUseCase authUseCase;

    @Test
    void shouldLoginSuccessfully() {

        Role role = new Role(1L,"ADMIN");

        User user = new User(
                1L,
                "Sebastian",
                "Gomez",
                123L,
                "+573005698325",
                LocalDate.of(2000,1,1),
                "test@test.com",
                "encryptedPassword",
                role
        );

        when(userPersistencePort.findOneByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoderPort.matches("123456", "encryptedPassword"))
                .thenReturn(true);

        when(jwtServicePort.generateToken(anyLong(),anyString(), anyString(), anyString()))
                .thenReturn("fake-jwt-token");

        AuthResponse authResponse = authUseCase.login(new LoginRequest ("test@test.com", "123456"));

        String token = authResponse.getToken();

        assertEquals("fake-jwt-token", token);

        verify(userPersistencePort).findOneByEmail("test@test.com");
        verify(passwordEncoderPort).matches("123456", "encryptedPassword");
        verify(jwtServicePort).generateToken(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getName()
        );
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(userPersistencePort.findOneByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        LoginRequest loginRequest = new LoginRequest("test@test.com", "123456");

        assertThrows(RuntimeException.class,
                () -> authUseCase.login(loginRequest));

        verify(passwordEncoderPort, never()).matches(any(), any());
        verify(jwtServicePort, never()).generateToken(anyLong(),any(), any(), any());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {

        Role role = new Role(1L,"ADMIN");
        User user = new User(
                1L,
                "Sebastian",
                "Gomez",
                123L,
                "+573005698325",
                LocalDate.of(2000,1,1),
                "test@test.com",
                "encryptedPassword",
                role
        );

        when(userPersistencePort.findOneByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoderPort.matches("123456", "encryptedPassword"))
                .thenReturn(false);

        LoginRequest loginRequest = new LoginRequest("test@test.com", "123456");

        assertThrows(RuntimeException.class,
                () -> authUseCase.login(loginRequest));

        verify(jwtServicePort, never()).generateToken(anyLong(),any(), any(), any());
    }
}