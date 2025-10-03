package com.xande.mongodb.relacionamentos.relacionamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RelacionamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelacionamentosApplication.class, args);
	}

}
