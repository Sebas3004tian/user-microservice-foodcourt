package com.foodcourt.user_microservice_foodcourt.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
}