package com.example.dddstart;

import com.example.dddstart.common.jpa.RangeableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories(repositoryBaseClass = RangeableRepositoryImpl.class)
@EnableAsync
@SpringBootApplication
public class DddStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddStartApplication.class, args);
    }

}
