package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.TrainingRoom;

public interface TrainingRepository extends CrudRepository<TrainingRoom, Long> {
	List<TrainingRoom> findBystatus(Boolean status);
}
