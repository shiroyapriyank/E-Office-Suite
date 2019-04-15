package com.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

@Table(name = "EmployeeDetails")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	private String empName;
	private String empLastName;
	private String empDesignation;
	private String empEmailID;
	private Date joiningDate;

	@JsonManagedReference
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private User user;

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empLastName=" + empLastName
				+ ", empDesignation=" + empDesignation + ", empEmailID=" + empEmailID + ", joiningDate=" + joiningDate
				+ "]";
	}

	public Employee() {
	}

	public Employee(Long empId, String empName, String empLastName, String empDesignation, String empEmailID,
			Date joiningDate, User user) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLastName = empLastName;
		this.empDesignation = empDesignation;
		this.empEmailID = empEmailID;
		this.joiningDate = joiningDate;
		this.user = user;
	}

	public Employee(Long empId, String empName, String empLastName, String empDesignation, String empEmailID,
			Date joiningDate) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLastName = empLastName;
		this.empDesignation = empDesignation;
		this.empEmailID = empEmailID;
		this.joiningDate = joiningDate;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpEmailID() {
		return empEmailID;
	}

	public void setEmpEmailID(String empEmailID) {
		this.empEmailID = empEmailID;
	}

}
