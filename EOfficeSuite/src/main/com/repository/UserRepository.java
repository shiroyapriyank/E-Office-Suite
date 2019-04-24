package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByempEmailID(String email);
	Boolean existsByempUserName(String username);
    Boolean existsByempEmailID(String email);
}
