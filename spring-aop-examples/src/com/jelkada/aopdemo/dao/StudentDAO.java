package com.jelkada.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class StudentDAO {
	
	public void addUser() {
		System.out.println("\n" + getClass() + " Process adding a student to the DB");
	}
}
