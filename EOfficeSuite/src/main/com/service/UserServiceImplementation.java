package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.mail.SendMail;
import com.model.Attendance;
import com.model.Employee;
import com.model.Leave;
import com.model.MeetingRoom;
import com.model.Role;
import com.model.Task;
import com.model.TrainingRoom;
import com.model.User;
import com.repository.AttendanceRepository;
import com.repository.EmployeeRepository;
import com.repository.LeaveRepository;
import com.repository.MeetingRepository;
import com.repository.TaskRepository;
import com.repository.TrainingRepository;
import com.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private TaskRepository taskRepo;
	@Autowired
	private MeetingRepository meetRepo;
	@Autowired
	private TrainingRepository trainRepo;
	@Autowired
	private AttendanceRepository attenRepo;
	@Autowired
	private LeaveRepository leaveRepo;

	private String password;

	@Override
	public String saveUser(Employee emp) {
		System.out.println("Name is : " + emp.getEmpName());
		User user = new User();
		SendMail mail = new SendMail();
		// save EMployee
		empRepo.save(emp);

		// getting name, last name and joiningDate for password and fname as UserName
		String firstName = emp.getEmpName();
		String lastName = emp.getEmpLastName();
		Date JoiningDate = emp.getJoiningDate();
		SimpleDateFormat sd = new SimpleDateFormat("MM-dd");
		String date = sd.format(JoiningDate);
		password = firstName.substring(0, 2) + "_" + lastName.substring(0, 2) + "_" + date;
		String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
		System.out.println("Encyrpted Password = " + hashPassword);
		System.out.println(password);
		user.setEmpEmailID(emp.getEmpEmailID());
		user.setEmpUserName(emp.getEmpName());
		user.setEmpPassword(hashPassword);
		user.setEmployee(emp);
		user.setRoles(Arrays.asList(new Role("USER")));
		// Save user
		userRepo.save(user);
		// send mail to Employee
		mail.sendMailForRegister(emp.getEmpEmailID(), emp, password);
		return "Success";
	}

	@Override
	public String saveTask(Task task) {
		System.out.println("Inside Assign Done");
		taskRepo.save(task);
		System.out.println("Task Assign Done");
		System.out.println("Sending Mail");
		SendMail mail = new SendMail();
		Optional<User> users = userRepo.findById(task.getEmpID());
		System.out.println(users);
		mail.sendMailForTaskAssign(users.get().getEmpEmailID(), task, users.get().getEmpUserName());
		System.out.println("Mail Send");
		return "Added";
	}

	@Override
	public List<Employee> listEmp() {

		List<Employee> list = (List<Employee>) empRepo.findAll();
		return list;
	}
	
	

	@Override
	public ResponseEntity<Leave> updateLeave(Long id, Boolean status) {
		Optional<Leave> leave = leaveRepo.findByempID(id);
		if(leave.isPresent()) {
			Leave l = leave.get();
			l.setStatus(status);
			System.out.println("Updated");
			return new ResponseEntity<>(leaveRepo.save(l), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<Employee> updateEmp(Long id, Employee emp) {
		Optional<Employee> employee = empRepo.findById(id);
		if (employee.isPresent()) {
			Employee e = employee.get();
			e.setEmpDesignation(emp.getEmpDesignation());
			e.setEmpName(emp.getEmpName());
			e.setEmpEmailID(emp.getEmpEmailID());
			e.setJoiningDate(emp.getJoiningDate());
			e.setEmpLastName(emp.getEmpLastName());
			e.setUser(emp.getUser());
			return new ResponseEntity<>(empRepo.save(e), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public List<Attendance> updateAttedance(String date, Long[] empId) throws ParseException {
		Attendance atten = new Attendance();
		List<Attendance> list = new ArrayList<>();
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = formatter2.parse(date);
		List<User> user = (List<User>) userRepo.findAll();
		user.forEach(System.out::println);
		Iterator<User> it = user.iterator();
		User userDetail = new User();
		while (it.hasNext()) {
			atten = new Attendance();
			userDetail = it.next();
			atten.setEmpId(userDetail.getEmpId());
			atten.setEmployeeName(userDetail.getEmpUserName());
			atten.setDate(date2);
			attenRepo.save(atten);
		}
		list.add(atten);
		for (Long id : empId) {
			List<Attendance> users = attenRepo.findByempIdAndDate(id, date2);
			if (!users.isEmpty()) {
				Iterator<Attendance> its = users.iterator();
				while (its.hasNext()) {
					Attendance a = its.next();
					a.setStatus(true);
					attenRepo.save(a);
				}
			}
		}

		return null;
	}

	@Override
	public ResponseEntity<MeetingRoom> addMeetingRequest(Long id, MeetingRoom getMeeting) {
		Optional<MeetingRoom> meeting = meetRepo.findById(getMeeting.getMeetingRoomId());
		System.out.println("outside");
		if (meeting.isPresent()) {
			System.out.println("inside");
			MeetingRoom meet = meeting.get();
			meet.setStatus(true);
			meet.setReason(getMeeting.getReason());
			meet.setEmpId(id);
			meet.setRequestedDate(getMeeting.getRequestedDate());
			return new ResponseEntity<>(meetRepo.save(meet), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<TrainingRoom> addTrainingRequest(Long id, TrainingRoom train) {
		Optional<TrainingRoom> meeting = trainRepo.findById(train.getTrainingRoomId());
		System.out.println("outside");
		if (meeting.isPresent()) {
			System.out.println("inside");
			TrainingRoom training = meeting.get();
			training.setStatus(true);
			training.setReason(train.getReason());
			training.setEmpId(id);
			training.setRequestedDate(train.getRequestedDate());
			return new ResponseEntity<>(trainRepo.save(training), HttpStatus.OK);
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

	@Override
	public ResponseEntity<Task> updateTask(Task[] task) {
		SendMail mail = new SendMail();
		User user = new User();
		for (Task task1 : task) {
			Optional<Task> tasks = taskRepo.findById(task1.getTaskID());
			Optional<User> userEmail = userRepo.findById(task1.getEmpID());
			if (tasks.isPresent()) {
				Task uTask = tasks.get();
				uTask.setEmpID(task1.getEmpID());
				uTask.setTaskName(task1.getTaskName());
				uTask.setTaskDescription(task1.getTaskDescription());
				uTask.setEndDate(task1.getEndDate());
				uTask.setStartDate(task1.getStartDate());
				uTask.setTaskID(task1.getTaskID());
				uTask.setStatus(task1.getStatus());
				mail.sendMailForTaskStatus(task1, userEmail.get().getEmpUserName());
				return new ResponseEntity<>(taskRepo.save(uTask), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
