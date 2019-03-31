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

		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setTo(emp.getEmpEmailID());
			helper.setText("Welcome to on-Board into E-office Corp.\nWelcome " + emp.getEmpName()
					+ ", your details as below:-\nLoginid: " + emp.getEmpEmailID() + "\nPassword: " + password
					+ "\n\n\nRegards.\nAdmin Team.\nE-Office Corp.");
			helper.setSubject("Welcome to e-Office Suite " + emp.getEmpName());

		} catch (MessagingException e) {
			e.printStackTrace();
			return "Exception While Sending Email";
		}

		sender.send(msg);
		return "Success";
	}

	@Override
	public String saveTask(Task task) {
		taskRepo.save(task);
		Optional<User> users = userRepo.findById(task.getEmpID());
		System.out.println(users);
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setTo(users.get().getEmpEmailID());
			System.out.println(users.get().getEmpEmailID());
			helper.setText("Hello " + users.get().getEmpUserName() + ",\nYou Have Assigned Task Below:-\n"
					+ "Task Subject: " + task.getTaskName() + "\nTask Description: " + task.getTaskDescription() + "\n"
					+ "Task Start Date: " + task.getStartDate() + "\n" + "Task End Date: " + task.getEndDate()
					+ "\n\n\n" + "Regards.\nAdmin Team.\nE-Office Corp.");
			helper.setSubject("Welcome to e-Office Suite " + users.get().getEmpUserName());
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Exception While Sending Email";
		}

		sender.send(msg);
		return "Added";
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { User user = userRepo.findByName(username);
	 * if(user == null){ throw new
	 * UsernameNotFoundException("Invalid username or password."); } return new
	 * org.springframework.security.core.userdetails.User(user.getEmpUserName(),
	 * user.getEmpPassword(), getAuthority()); } private
	 * List<SimpleGrantedAuthority> getAuthority() { return Arrays.asList(new
	 * SimpleGrantedAuthority("ROLE_ADMIN")); }
	 */

}
