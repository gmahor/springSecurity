package com.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.dao.UserRepo;
import com.springsecurity.entities.User;

@Controller
public class TestController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/")
	public String homepage() {
		return "index";
	}

	@RequestMapping("/signup")
	public String register() {
		return "signup";
	}

	@PostMapping("/signupProcess")
	public String registerProcess(@ModelAttribute User user) {

		try {

			user.setRole("ROLE_USER");

			String password = bCryptPasswordEncoder.encode(user.getPassword());

			user.setPassword(password);

			this.userRepo.save(user);

			System.out.println("User Added Successfully....");

			return "index";

		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/admin/admindashboard")
	public String adminForm() {
		return "admindashboard";
	}

	@GetMapping("/user/userdashboard")
	public String userForm() {
		return "userdashboard";
	}

}
