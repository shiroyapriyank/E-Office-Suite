package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.MeetingRoom;

public interface MeetingRepository extends CrudRepository<MeetingRoom, Long> {
	List<MeetingRoom> findBystatus(Boolean status);
}
