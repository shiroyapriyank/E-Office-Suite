package com.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.model.Leave;

public interface LeaveRepository extends CrudRepository<Leave, Long> {

	Optional<Leave> findByempID(Long id);
	
}
