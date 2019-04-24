package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Attendance;
import com.model.Employee;
import com.model.Leave;
import com.model.MeetingRoom;
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
import com.service.UserService;

@RestController
@RequestMapping("/index")
public class Controller {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private LeaveRepository leaveRepo;

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private MeetingRepository meetRepo;

	@Autowired
	private AttendanceRepository attenRepo;

	@Autowired
	private TrainingRepository trainRepo;

	@GetMapping("/")
	public String home() {
		return "Hello";
	}

	@GetMapping("/private")
	public String privateArea() {
		return "private";
	}

	@PostMapping("/admin/addemployee")
	public String addEmp(@RequestBody Employee emp) {
		return userService.saveUser(emp);
	}

	@GetMapping(value = "/admin/listLeave", produces = "application/json")
	public List<Leave> listLeave() {
		return (List<Leave>) leaveRepo.findAll();
	}

	@GetMapping(value = "/admin/listEmployee", produces = "application/json")
	public List<Employee> listEmployee() {
		return (List<Employee>) empRepo.findAll();
	}

	@GetMapping(value = "/admin/listTask", produces = "application/json")
	public List<Task> listTask() {
		return (List<Task>) taskRepo.findAll();
	}

	@GetMapping(value = "/admin/listTrainingRoom", produces = "application/json")
	public List<TrainingRoom> listTrainingRoom() {
		return (List<TrainingRoom>) trainRepo.findAll();
	}

	@GetMapping(value = "/admin/listMeetingRoom", produces = "application/json")
	public List<MeetingRoom> listMeetingRoom() {
		return (List<MeetingRoom>) meetRepo.findAll();
	}

	@GetMapping(value = "/admin/listTrainingRoomwithStatusFlase", produces = "application/json")
	public List<TrainingRoom> listTrainingRoomwithStatus() {
		return (List<TrainingRoom>) trainRepo.findBystatus(false);
	}

	@GetMapping(value = "/admin/listMeetingRoomwithStatusFlase", produces = "application/json")
	public List<MeetingRoom> listMeetingRoomwithStatus() {
		return meetRepo.findBystatus(false);
	}

	@PutMapping("/admin/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee emp) {
		List<Employee> list = new ArrayList<Employee>();
		list.add(emp);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return userService.updateEmp(id, emp);
	}
	
	@PutMapping("/admin/updateLeaveRequest/{id}/{status}")
	public ResponseEntity<Leave> updateLeaveRequest(@PathVariable("id") Long id, @PathVariable Boolean status){
		System.out.println("id= "+id+"|| status= "+status);
		return userService.updateLeave(id,status);
	}

	@PutMapping("/user/updateMeeting/{id}")
	public ResponseEntity<MeetingRoom> addMeetingRequest(@PathVariable("id") Long id, @RequestBody MeetingRoom meet) {
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		list.add(meet);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return userService.addMeetingRequest(id, meet);
	}

	@PutMapping("/user/updateTraining/{id}")
	public ResponseEntity<TrainingRoom> addTrainingRequest(@PathVariable("id") Long id,
			@RequestBody TrainingRoom train) {
		List<TrainingRoom> list = new ArrayList<TrainingRoom>();
		list.add(train);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return userService.addTrainingRequest(id, train);
	}

	@PutMapping(value = "/user/updateTask")
	public ResponseEntity<Task> updateTask(@RequestBody Task task[]) {
		for (Task task1 : task)
			System.out.println(task1);
		return userService.updateTask(task);
	}

	@PostMapping("/admin/assignTask")
	public String assignTask(@RequestBody Task task) {
		List<Task> list = new ArrayList<Task>();
		list.add(task);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return userService.saveTask(task);
	}

	@PostMapping("/admin/addTrainingRoom")
	public TrainingRoom addTrainingRoom(@RequestBody TrainingRoom train) {
		List<TrainingRoom> list = new ArrayList<>();
		list.add(train);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return trainRepo.save(train);
	}

	@PostMapping("/admin/isPresent/{date}")
	public List<Attendance> isPresent(@PathVariable("date") String date, @RequestBody Long[] empIds) throws ParseException {
		return userService.updateAttedance(date, empIds);
	}

	@PostMapping("/admin/addMeetingRoom")
	public MeetingRoom addMeetingRoom(@RequestBody MeetingRoom meeting) {
		List<MeetingRoom> list = new ArrayList<>();
		list.add(meeting);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		return meetRepo.save(meeting);
	}

	@PostMapping("/user/leaveRequest")
	public Leave requestLeave(@RequestBody Leave leave) {
		List<Leave> list = new ArrayList<>();
		list.add(leave);
		System.out.println("---------------------");
		list.forEach(System.out::println);
		System.out.println("---------------------");
		Optional<User> us = userRepo.findById(leave.getEmpID());
		String name = us.get().getEmpUserName();
		leave.setEmpName(name);
		return leaveRepo.save(leave);
	}

	@GetMapping("/admin/getById/{id}")
	public List<Employee> findEmpById(@PathVariable Long id) {
		List<Employee> emp = userService.findById(id);
		return emp;
	}

	@GetMapping("/user/getTaskById/{id}")
	public List<Task> findTaskByEmpID(@PathVariable Long id) {
		List<Task> task = taskRepo.findByEmpID(id);
		return task;
	}

	@GetMapping("/user/checkEmail/{email}")
	public Employee findByEmailID(@PathVariable String email) {
		return empRepo.findByempEmailID(email);
	}

	@GetMapping("/admin/getAttendanceByDate/{date}")
	public List<Attendance> getAttendanceByDate(@PathVariable String date) throws ParseException {
		System.out.println(date);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = formatter2.parse(date);
		return attenRepo.findBydate(date2);
	}
	
	@GetMapping("/admin/getAttendanceByDateAndId/{date}/{id}")
	public List<Attendance> getAttendanceByDateAndId(@PathVariable String date, @PathVariable Long id) throws ParseException {
		System.out.println(date);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = formatter2.parse(date);
		return attenRepo.findByempIdAndDate(id, date2);
	}

}
