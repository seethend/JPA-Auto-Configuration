package com.seeth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com"})
public class SpringAutoConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAutoConfigurationApplication.class, args);
	}

}
