package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.model.Employee;
import com.model.Task;

public interface UserService {
	List<Employee> listEmp();
	String saveUser(Employee emp);
	String saveTask(Task task);
	ResponseEntity<Employee> updateEmp(Long id, Employee emp);
	List<Employee> findById(Long id);
}
