package com.pietrobraga.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pietrobraga.workshopmongo.domain.Post;
import com.pietrobraga.workshopmongo.repositories.PostRepository;
import com.pietrobraga.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository repository;

	public Post findById(String id) {
		Post post = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
		return post;
	}
	
	public List<Post> findByTitle(String text){
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
}
