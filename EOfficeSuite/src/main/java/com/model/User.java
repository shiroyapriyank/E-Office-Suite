package com.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "User")
public class User {

	@Id
	@Column(name = "user_ID")
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "employee"))
	private Long empId;
	private String empEmailID;
	private String empPassword;
	private String empUserName;
	@JsonBackReference
	@OneToOne
	@PrimaryKeyJoinColumn
	private Employee employee;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;
	
	@Override
	public String toString() {
		return "User [empId=" + empId + ", empEmailID=" + empEmailID + ", empPassword=" + empPassword + ", empUserName="
				+ empUserName + "]";
	}
	
	public User(){}

	public User(String empEmailID, String empPassword, List<Role> roles) {
		super();
		this.empEmailID = empEmailID;
		this.empPassword = empPassword;
		this.roles = roles;
	}





	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmpEmailID() {
		return empEmailID;
	}
	public void setEmpEmailID(String empEmailID) {
		this.empEmailID = empEmailID;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpUserName() {
		return empUserName;
	}
	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
