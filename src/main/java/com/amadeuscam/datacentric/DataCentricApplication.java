package com.amadeuscam.datacentric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DataCentricApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataCentricApplication.class, args);
    }


}
