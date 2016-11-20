package pl.ething.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "pl.ething.model")
@EnableJpaRepositories(basePackages = "pl.ething.repository")
@ComponentScan(basePackages = {"pl.ething.config", "pl.ething.controller", "pl.ething.projection"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

};
