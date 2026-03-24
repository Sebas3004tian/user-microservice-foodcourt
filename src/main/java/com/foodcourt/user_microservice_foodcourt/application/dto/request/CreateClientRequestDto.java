package com.foodcourt.user_microservice_foodcourt.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientRequestDto {

    @NotBlank(message="The name cannot be empty")
    private String name;

    @NotBlank(message="The lastName cannot be empty")
    private String lastName;

    @NotNull(message="The identificationNumber cannot be empty")
    @Positive(message="The identificationNumber cannot be negative")
    private Long identificationNumber;

    @NotBlank(message="The phoneNumber cannot be empty")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message="The email cannot be empty")
    @Email(message="It must be in the format @example.com")
    private String email;

    @NotBlank(message="The password cannot be empty")
    private String password;
}
