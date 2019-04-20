package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	List<Task> findByEmpID(Long id);
}
