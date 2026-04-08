package com.foodcourt.user_microservice_foodcourt.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message="The email cannot be empty")
    @Email(message="It must be in the format @example.com")
    private String email;

    @NotBlank(message="The password cannot be empty")
    private String password;
}
