package com.seeth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seeth.models.MyUser;


public interface MyUserRepository extends JpaRepository<MyUser, String> {

}
