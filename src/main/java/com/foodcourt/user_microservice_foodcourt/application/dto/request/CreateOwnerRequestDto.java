package com.foodcourt.user_microservice_foodcourt.application.dto.request;

import com.foodcourt.user_microservice_foodcourt.domain.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateOwnerRequestDto {
    private String name;
    private String lastName;
    private Long id;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private UserRole role;
}
