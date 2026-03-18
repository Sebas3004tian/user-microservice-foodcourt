package com.foodcourt.user_microservice_foodcourt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class}
)
public class UserMicroserviceFoodcourtApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceFoodcourtApplication.class, args);
	}

}
