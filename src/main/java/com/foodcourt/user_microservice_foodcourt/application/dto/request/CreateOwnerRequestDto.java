package com.foodcourt.user_microservice_foodcourt.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateOwnerRequestDto {

    @NotBlank(message="The name cannot be empty")
    private String name;

    @NotBlank(message="The lastName cannot be empty")
    private String lastName;

    @NotNull(message="The id cannot be empty")
    @Positive(message="The id cannot be negative")
    private Long id;

    @NotBlank(message="The phoneNumber cannot be empty")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message="The phoneNumber cannot be empty")
    private LocalDate birthDate;

    @NotBlank(message="The email cannot be empty")
    @Email(message="It must be in the format @example.com")
    private String email;

    @NotBlank(message="The password cannot be empty")
    private String password;
}
