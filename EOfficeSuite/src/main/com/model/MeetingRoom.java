package com.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "meeting_room", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class MeetingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long meetingRoomId;
	private Long empId;
	@NotBlank
	private String name;
	private String floor;
	private String capacity;
	private boolean videoConference;
	private boolean audioConference;
	private boolean status;
	private String reason;
	private Date requestedDate;
	private boolean apporved;
	
	
	
	
	public boolean isApporved() {
		return apporved;
	}

	public void setApporved(boolean apporved) {
		this.apporved = apporved;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
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

	public Long getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(Long meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
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

	public boolean isVideoConference() {
		return videoConference;
	}

	public void setVideoConference(boolean videoConference) {
		this.videoConference = videoConference;
	}

	public boolean isAudioConference() {
		return audioConference;
	}

	public void setAudioConference(boolean audioConference) {
		this.audioConference = audioConference;
	}

	@Override
	public String toString() {
		return "MeetingRoom [meetingRoomId=" + meetingRoomId + ", name=" + name + ", floor=" + floor + ", capacity="
				+ capacity + ", videoConference=" + videoConference + ", audioConference=" + audioConference + "]";
	}

}
