package com.tomcat.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SampleTomcatSslApplication {
	public static void main(String[] args) {
		SpringApplication.run(SampleTomcatSslApplication.class, args);
	}
}
