package com.foodcourt.user_microservice_foodcourt;

import com.foodcourt.user_microservice_foodcourt.domain.model.Role;
import com.foodcourt.user_microservice_foodcourt.domain.model.User;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IPasswordEncoderPort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IRolePersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.spi.IUserPersistencePort;
import com.foodcourt.user_microservice_foodcourt.domain.usecase.UserUseCase;
import com.foodcourt.user_microservice_foodcourt.infrastructure.exception.UserAlreadyExistsException;
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
class CreateEmployeeUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void shouldCreateEmployeeSuccessfully() {

        Role role = new Role(3L, "EMPLEADO");

        User user = new User(
                "Sebastian",
                "Gomez",
                123L,
                "+573005698325",
                LocalDate.of(2016,1,1), // 👈 puede ser menor
                "test@test.com",
                "123456",
                role
        );

        when(rolePersistencePort.findOneByName("EMPLEADO"))
                .thenReturn(Optional.of(role));

        when(passwordEncoderPort.encode("123456"))
                .thenReturn("encryptedPassword");

        userUseCase.createEmployee(user);

        verify(passwordEncoderPort).encode("123456");
        verify(userPersistencePort).createUser(user);

        assertEquals("encryptedPassword", user.getPassword());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeAlreadyExists() {

        Role role = new Role(3L, "EMPLEADO");

        User user = new User(
                "Sebastian",
                "Gomez",
                123L,
                "+573005698325",
                LocalDate.of(2000,1,1),
                "test@test.com",
                "123456",
                role
        );

        when(rolePersistencePort.findOneByName("EMPLEADO"))
                .thenReturn(Optional.of(role));

        when(passwordEncoderPort.encode(anyString()))
                .thenReturn("encryptedPassword");

        doThrow(new UserAlreadyExistsException("User already exists"))
                .when(userPersistencePort).createUser(any());

        assertThrows(UserAlreadyExistsException.class, () -> {
            userUseCase.createEmployee(user);
        });

        verify(passwordEncoderPort).encode("123456");
    }

    @Test
    void shouldEncryptPasswordBeforeSavingEmployee() {

        Role role = new Role(3L, "EMPLEADO");

        User user = new User(
                "Sebastian",
                "Gomez",
                123L,
                "+573005698325",
                LocalDate.of(2000,1,1),
                "test@test.com",
                "123456",
                role
        );

        when(rolePersistencePort.findOneByName("EMPLEADO"))
                .thenReturn(Optional.of(role));

        when(passwordEncoderPort.encode("123456"))
                .thenReturn("encryptedPassword");

        userUseCase.createEmployee(user);

        assertEquals("encryptedPassword", user.getPassword());
        assertNotEquals("123456", user.getPassword());

        verify(userPersistencePort).createUser(user);
    }
}
