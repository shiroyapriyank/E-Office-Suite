package com.service;

import com.model.Employee;
import com.model.Task;

public interface UserService {

	String saveUser(Employee emp);
	String saveTask(Task task);
}
