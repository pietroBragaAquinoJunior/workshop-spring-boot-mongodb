package com.pietrobraga.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pietrobraga.workshopmongo.domain.User;
import com.pietrobraga.workshopmongo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
}
