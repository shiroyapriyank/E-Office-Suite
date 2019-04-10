package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mail.SendMail;
import com.model.Employee;
import com.model.Task;
import com.model.User;
import com.repository.EmployeeRepository;
import com.repository.TaskRepository;
import com.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private TaskRepository taskRepo;

	private String password;

	@Override
	public String saveUser(Employee emp) {
		System.out.println("Name is : " + emp.getEmpName());
		User user = new User();
		SendMail mail = new SendMail();
		//save EMployee
		empRepo.save(emp);
		
		//getting name, last name and joiningDate for password and fname as UserName
		String firstName = emp.getEmpName();
		String lastName = emp.getEmpLastName();
		Date JoiningDate = emp.getJoiningDate();
		SimpleDateFormat sd = new SimpleDateFormat("MM-dd");
		String date = sd.format(JoiningDate);
		password = firstName.substring(0, 2) + "_" + lastName.substring(0, 2) + "_" + date;
		System.out.println(password);
		user.setEmpEmailID(emp.getEmpEmailID());
		user.setEmpUserName(emp.getEmpName());
		user.setEmpPassword(password);
		user.setEmployee(emp);
		//Save user
		userRepo.save(user);
		//send mail to Employee
		mail.sendMailForRegister(emp.getEmpEmailID(),emp,password);
		return "Success";
	}

	@Override
	public String saveTask(Task task) {
		taskRepo.save(task);
		SendMail mail = new SendMail();
		Optional<User> users = userRepo.findById(task.getEmpID());
		System.out.println(users);
		mail.sendMailForTaskAssign(users.get().getEmpEmailID(), task, users.get().getEmpUserName());
		return "Added";
	}

	@Override
	public List<Employee> listEmp() {
		
		List<Employee> list = (List<Employee>) empRepo.findAll();
		return list;
	}
	
	@Override
	public ResponseEntity<Employee> updateEmp(Long id, Employee emp){
		Optional<Employee> employee = empRepo.findById(id);
		if(employee.isPresent()) {
			Employee e = employee.get();
			e.setEmpDesignation(emp.getEmpDesignation());
			e.setEmpName(emp.getEmpName());
			e.setEmpEmailID(emp.getEmpEmailID());
			e.setJoiningDate(emp.getJoiningDate());
			e.setEmpLastName(emp.getEmpLastName());
			e.setUser(emp.getUser());
			return new ResponseEntity<>(empRepo.save(e),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Employee> findById(Long id) {
		Optional<Employee> list = empRepo.findById(id);
		Employee lists = new Employee();
		if (list.isPresent()) {
			lists = list.get();
		}
		List<Employee> listemp = new ArrayList<Employee>();
		listemp.add(lists);
		return listemp;
		
	}
	
	


}
