package com.dca.company.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Created by denis on 10/02/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.dca.company.service", "com.dca.company.controller.controller"})
@EntityScan(basePackages = "com.dca.company.model.entity")
@EnableJpaRepositories(basePackages = "com.dca.company.model.repository")
@EnableAutoConfiguration
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
