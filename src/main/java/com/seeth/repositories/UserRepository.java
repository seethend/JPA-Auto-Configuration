package com.seeth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeth.models.User;
import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByName(String name);
	
}
