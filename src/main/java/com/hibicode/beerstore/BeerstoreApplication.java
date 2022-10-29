package com.hibicode.beerstore;

import com.hibicode.beerstore.model.Beer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.hibicode.beerstore.repository")
@EntityScan(basePackageClasses = Beer.class)
public class BeerstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerstoreApplication.class, args);
	}
}
