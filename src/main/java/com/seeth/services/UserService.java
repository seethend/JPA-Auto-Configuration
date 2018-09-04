package com.seeth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeth.models.User;
import com.seeth.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String getEmail(String username) {
		String email = "Not Found";
		if(userRepository.findByName(username).stream().findFirst().isPresent()) {
			email = userRepository.findByName(username).stream().findFirst().get().getEmail();
		}
		return email;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
}
