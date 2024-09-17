package com.urbanbazaar;

import com.urbanbazaar.Repo.jpa.UserAuthRepo;
import com.urbanbazaar.Repo.mongo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ProductRepo.class)
@EnableJpaRepositories(basePackageClasses = UserAuthRepo.class)
public class UrbanbazaarApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrbanbazaarApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
