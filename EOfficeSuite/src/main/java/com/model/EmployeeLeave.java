package com.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class EmployeeLeave {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leaveID;
    private String userName;
    private Long empID;
    private Date leaveDate;
    private String leaveRea1son;
    private boolean status;
	public Long getLeaveID() {
		return leaveID;
	}
	public void setLeaveID(Long leaveID) {
		this.leaveID = leaveID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getEmpID() {
		return empID;
	}
	public void setEmpID(Long empID) {
		this.empID = empID;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getLeaveRea1son() {
		return leaveRea1son;
	}
	public void setLeaveRea1son(String leaveRea1son) {
		this.leaveRea1son = leaveRea1son;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "EmployeeLeave [leaveID=" + leaveID + ", userName=" + userName + ", empID=" + empID + ", leaveRea1son="
				+ leaveRea1son + ", status=" + status + "]";
	}
    
    
}
