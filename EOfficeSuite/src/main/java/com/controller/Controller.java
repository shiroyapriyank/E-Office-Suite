package com.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.model.Employee;
import com.model.Task;
import com.repository.EmployeeRepository;
import com.repository.TaskRepository;
import com.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Controller {

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private TaskRepository taskRepo;

	@GetMapping("/")
	public String home() {
		return "Hello";
	}
	
	@GetMapping("/private")
	public String privateArea() {
		return "private";
	}
	
	
	@PostMapping("/addemployee")
	public String addEmp(@RequestBody Employee emp) {
		return userService.saveUser(emp);
	}

	@GetMapping(value="/listEmployee",produces = "application/json")
	public List<Employee> listEmployee(){
		return (List<Employee>) empRepo.findAll();
	}

	@GetMapping(value="/listTask",produces = "application/json")
	public List<Task> listTask(){
		return (List<Task>) taskRepo.findAll();
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee emp){
		List<Employee> list = new ArrayList<Employee>();
		list.add(emp);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return userService.updateEmp(id, emp);
	}

	@PostMapping("/assignTask")
	public String assignTask(@RequestBody Task task) {
		List<Task> list = new ArrayList<Task>();
		list.add(task);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");		
		return userService.saveTask(task);
	}

	@PostMapping("/empTaskStatus")
	public String empTaskStatus(@RequestBody Task task) {
		return null;
	}

	@GetMapping("/getById/{id}")
	public List<Employee> findEmpById(@PathVariable Long id){
		List<Employee> emp = userService.findById(id);
		return emp;
	}

}
