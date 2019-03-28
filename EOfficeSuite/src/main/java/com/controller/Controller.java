package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.service.UserService;

@RestController
public class Controller {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addemployee")
	public String addEmp(@Valid Employee emp) {
		userService.saveUser(emp);
		return "success";
	}

}
