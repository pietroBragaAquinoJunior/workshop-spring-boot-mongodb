package com.pietrobraga.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pietrobraga.workshopmongo.domain.Post;
import com.pietrobraga.workshopmongo.domain.User;
import com.pietrobraga.workshopmongo.dto.UserDTO;
import com.pietrobraga.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = userService.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
		
		User user = userService.fromDTO(userDto);
		userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User user = userService.findById(id);
		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok().body(userDTO);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		
		userService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO userDto, @PathVariable String id){
		
		User user = userService.fromDTO(userDto);
		user.setId(id);
		userService.update(user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
		
	}
	
}
