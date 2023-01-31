package com.jelkada.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		
		System.out.println("getDailyWorkout(): " + theCoach.getDailyWorkout());
		System.out.println("getDailyFortune(): " + theCoach.getDailyFortune());
	
		System.out.println("getEmail(): " + theCoach.getEmail());
		System.out.println("getTeam(): " + theCoach.getTeam());
		
		context.close();
	}

}
