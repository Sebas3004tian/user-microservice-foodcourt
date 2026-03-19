package com.foodcourt.user_microservice_foodcourt.domain.spi;

public interface IPasswordEncoderPort {
    String encode(String rawPassword);
}
