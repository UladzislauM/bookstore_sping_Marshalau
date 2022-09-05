package com.company;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class ContextConfiguration {

    @Bean
    public EntityManagerFactory factory() {
        return Persistence.createEntityManagerFactory("nlsojpao_psql");
    }

    @Bean
    public EntityManager entityManager() {
        return factory().createEntityManager();
    }
}
