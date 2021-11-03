package com.example.calculus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableMongoRepositories
public class BancodeperguntasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancodeperguntasApplication.class, args);
	}

}
