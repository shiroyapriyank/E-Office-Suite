package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.model.TrainingRoom;

public interface TrainingRepository extends CrudRepository<TrainingRoom, Long> {

}
