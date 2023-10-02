package com.rocha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rocha.config.property.TokenApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(TokenApiProperty.class)
public class TFSTaskListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TFSTaskListApplication.class, args);
	}

}
