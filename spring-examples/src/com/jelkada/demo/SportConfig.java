package com.jelkada.demo;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sport.properties")
public class SportConfig {
	
	@Bean
	public FortuneService randomFortuneService() {
		return new RandomFortuneService();
	}

	@Bean
	public Coach swimCoach() {
		return new SwimCoach(randomFortuneService());
	}
}
