package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.model.Task;
import com.service.UserService;

@RestController
public class Controller {

	
	@Autowired
	private UserService userService;

	
	@PostMapping("/addemployee")
	public String addEmp(@Valid Employee emp) {
		return userService.saveUser(emp);
	}
	
	
	@PostMapping("/assignTask")
	public String assignTask(@Valid Task task) {
		return userService.saveTask(task);
	}
}
