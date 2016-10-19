package pl.ormrest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "pl.ormrest.model")
@EnableJpaRepositories(basePackages = "pl.ormrest.repository")
@ComponentScan(basePackages = {"pl.ormrest.config", "pl.ormrest.controller", "pl.ormrest.projection"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
};
