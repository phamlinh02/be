package com.maidoo.maidoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.maidoo.maidoo.config.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ ApplicationProperties.class})
@EnableScheduling
@EnableAsync
public class MaidooApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaidooApplication.class, args);
	}

}
