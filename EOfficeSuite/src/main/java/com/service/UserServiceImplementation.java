package com.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.model.User;
import com.repository.EmployeeRepository;
import com.repository.UserRepository;
@Service(value="userService")
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private JavaMailSender sender;

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
			helper.setText("Welcome " + emp.getEmpName() + ". Your userName is : " + emp.getEmpEmailID()
					+ " and Your Password is " + password);
			helper.setSubject("Login Credential");

		} catch (MessagingException e) {
			e.printStackTrace();
			return "Exception While Sending Email";
		}

		sender.send(msg);
		return "Success";
	}

}
