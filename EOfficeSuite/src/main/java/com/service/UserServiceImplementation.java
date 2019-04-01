package com.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	private JavaMailSender sender;
	@Autowired
	private TaskRepository taskRepo;

	private String password;

	@Override
	public String saveUser(Employee emp) {
		User user = new User();
		SendMail mail = new SendMail();
		empRepo.save(emp);
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

		userRepo.save(user);
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


}
