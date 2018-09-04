package com.seeth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seeth.models.MyUser;
import com.seeth.repositories.MyUserRepository;

@RestController
public class UserController {

	@Autowired
	MyUserRepository myUserRepository;
	
	@GetMapping("all")
	public List<MyUser> allUsers() {
		return myUserRepository.findAll();
	}
	
	@PostMapping("save")
	public MyUser getUser(@RequestBody MyUser user) {
		return myUserRepository.save(user);
	}
	
}
