package com.hodan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.hodan")
@EntityScan(basePackages = "com.hodan.dto.entity")
@EnableJpaRepositories(basePackages = "com.hodan.model.persistence")
public class SpringbootMvcDemoProjectDvdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMvcDemoProjectDvdApplication.class, args);
	}

}
