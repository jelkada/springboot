package com.jelkada.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("userLogin")
	public String showUserLogin() {
		
		return "user-login";
	}
	
	@GetMapping("/accessDenied")
	public String showAccessDenied() {
		
		return "access-denied";
	}
}
