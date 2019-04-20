package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "meeting_room", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class MeetingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long meetingRoomId;
	@NotBlank
	private String name;
	private String floor;
	private String capacity;
	private boolean videoConference;
	private boolean audioConference;
	private boolean status;

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
