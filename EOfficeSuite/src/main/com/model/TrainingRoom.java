package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrainingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trainingRoomId;
	private Long empId;
	private String name;
	private String floor;
	private String capacity;
	private boolean status;
	private String reason;
	private String requestedDate;
	private boolean apporved;
	
	
	

	
	
	public boolean isApporved() {
		return apporved;
	}

	public void setApporved(boolean apporved) {
		this.apporved = apporved;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getTrainingRoomId() {
		return trainingRoomId;
	}

	public void setTrainingRoomId(Long trainingRoomId) {
		this.trainingRoomId = trainingRoomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "TrainingRoom [trainingRoomId=" + trainingRoomId + ", name=" + name + ", floor=" + floor + ", capacity="
				+ capacity + "]";
	}

}
