package com.kicki.backend.crud.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kicki.backend.crud.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
}