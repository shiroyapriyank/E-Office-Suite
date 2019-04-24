package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empLeave")
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leaveID;
	private Long empID;
	private String empName;
	private Date leaveDate;
	private String reasonOfLeave;
	private boolean status;
	
	
	public Long getLeaveID() {
		return leaveID;
	}
	public void setLeaveID(Long leaveID) {
		this.leaveID = leaveID;
	}
	public Long getEmpID() {
		return empID;
	}
	public void setEmpID(Long empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getReasonOfLeave() {
		return reasonOfLeave;
	}
	public void setReasonOfLeave(String reasonOfLeave) {
		this.reasonOfLeave = reasonOfLeave;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Leave [leaveID=" + leaveID + ", empID=" + empID + ", empName=" + empName + ", leaveDate=" + leaveDate
				+ ", reasonOfLeave=" + reasonOfLeave + ", status=" + status + "]";
	}
	
	
}
