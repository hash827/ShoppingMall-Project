package com.example.onemind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableScheduling

public class JpetstoreBootApplication extends SpringBootServletInitializer {
	
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JpetstoreBootApplication.class);
	}

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JpetstoreBootApplication.class, args);
    }
}