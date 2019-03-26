package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.EmployeeRepository;
import com.model.Employee;

@RestController
public class Controller {
	
	private final EmployeeRepository empRepository;
	
	@Autowired
	public Controller(EmployeeRepository employeeRespository) {
		this.empRepository = employeeRespository;
	}
	
	@PostMapping("/addemployee")
	public String addEmp(@Valid Employee emp) {
		empRepository.save(emp);
		return "success";
	}


}
