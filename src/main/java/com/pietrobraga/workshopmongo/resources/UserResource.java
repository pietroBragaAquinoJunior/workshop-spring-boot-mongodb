package com.pietrobraga.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pietrobraga.workshopmongo.domain.User;
import com.pietrobraga.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll(){

		return ResponseEntity.ok().body(userService.findAll());
		
	}
	
}
