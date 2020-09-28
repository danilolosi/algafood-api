package com.danilolosi.algafoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.danilolosi.algafoodapi.infrastructure.repository.JpaRepositoryCustomImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaRepositoryCustomImpl.class)
public class AlgafoodApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgafoodApiApplication.class, args);
    }

}
