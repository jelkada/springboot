package com.jelkada.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.jelkada.aopdemo.User;

@Component
public class MemberDAO {
	
	public String addUser(User user) {
		System.out.println("\n" + getClass() + " Process adding a member to the DB");
		return "userId1";
	}
	
	public User updateUser(User user) {
		System.out.println("\n" + getClass() + " Process updating a member to the DB");
		user.setFirstName("David");
		return user;
	}
}
