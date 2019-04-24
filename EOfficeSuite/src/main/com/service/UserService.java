package com.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.model.Attendance;
import com.model.Employee;
import com.model.Leave;
import com.model.MeetingRoom;
import com.model.Task;
import com.model.TrainingRoom;

public interface UserService {
	List<Employee> listEmp();
	String saveUser(Employee emp);
	String saveTask(Task task);
	ResponseEntity<Employee> updateEmp(Long id, Employee emp);
	ResponseEntity<MeetingRoom> addMeetingRequest(Long id, MeetingRoom meet);
	ResponseEntity<TrainingRoom> addTrainingRequest(Long id, TrainingRoom train);
	List<Employee> findById(Long id);
	ResponseEntity<Task> updateTask(Task[] task);
	List<Attendance> updateAttedance(String Date, Long[] empId) throws ParseException;
	ResponseEntity<Leave> updateLeave(Long id, Boolean status);
}
