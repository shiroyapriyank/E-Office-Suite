package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.model.MeetingRoom;

public interface MeetingRepository extends CrudRepository<MeetingRoom, Long> {

}
