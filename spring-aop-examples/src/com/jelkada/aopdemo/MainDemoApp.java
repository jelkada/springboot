package com.jelkada.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jelkada.aopdemo.dao.MemberDAO;
import com.jelkada.aopdemo.dao.StudentDAO;
import com.jelkada.aopdemo.service.TrafficService;

public class MainDemoApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopDemoConfig.class);
		
		StudentDAO theStudentDAO = context.getBean("studentDAO", StudentDAO.class); 
		MemberDAO theMemberDAO = context.getBean("memberDAO", MemberDAO.class); 
		
		theStudentDAO.addUser();
		User user1 = new User("Jimmy", "Elkada", "jelkada@gmail.com");
		theMemberDAO.addUser(user1);
		theMemberDAO.updateUser(user1);
		
		TrafficService trafficService = context.getBean("trafficService", TrafficService.class);
		System.out.println("\n >> " + trafficService.slowTraffic());
		
		context.close();
	}

}
