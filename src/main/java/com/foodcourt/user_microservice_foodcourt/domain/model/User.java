package com.foodcourt.user_microservice_foodcourt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String lastName;
    private Long id;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;

    private UserRole role;
}
