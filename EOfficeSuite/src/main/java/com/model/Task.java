package com.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "TaskDetails")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long taskID;
	private String taskName;
	private String taskDescription;
	private Date startDate;
	private Date endDate;
	private Long empID;
	private String status;

	
	public Long getTaskID() {
		return taskID;
	}

	public void setTaskID(Long taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getEmpID() {
		return empID;
	}

	public void setEmpID(Long empID) {
		this.empID = empID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", empID=" + empID + ", status=" + status + "]";
	}
	
	

}
