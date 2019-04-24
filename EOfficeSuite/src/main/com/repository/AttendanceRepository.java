package com.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.model.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
	List<Attendance> findBydate(Date date);

	List<Attendance> findByempIdAndDate(Long id,Date date);
}
