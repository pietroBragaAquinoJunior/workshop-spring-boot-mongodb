package com.pietrobraga.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pietrobraga.workshopmongo.domain.User;
import com.pietrobraga.workshopmongo.dto.UserDTO;
import com.pietrobraga.workshopmongo.repositories.UserRepository;
import com.pietrobraga.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		User user = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
		return user;
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = repository.findById(user.getId()).get();
		updateData(newUser, user);
		return repository.save(newUser);
	}

	private void updateData(User newUser, User user) {
		
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		
	}
	
}
