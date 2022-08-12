package com.pietrobraga.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pietrobraga.workshopmongo.domain.Post;
import com.pietrobraga.workshopmongo.domain.User;
import com.pietrobraga.workshopmongo.dto.AuthorDTO;
import com.pietrobraga.workshopmongo.dto.CommentDTO;
import com.pietrobraga.workshopmongo.repositories.PostRepository;
import com.pietrobraga.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null,"Partiu viagem!", sdf.parse("21/03/2018"), "Vou viajar para São Paulo, Abraços!", new AuthorDTO(maria));
		Post p2 = new Post(null,"Hora de estudar!", sdf.parse("23/03/2018"), "Adoro estudar programação!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Comentário do alex!", sdf.parse("13/01/1999"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Comentário da maria!", sdf.parse("14/01/1999"), new AuthorDTO(maria));
		CommentDTO c3 = new CommentDTO("Comentário do bob!", sdf.parse("15/01/1999"), new AuthorDTO(bob));
		
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		
		userRepository.saveAll(Arrays.asList(maria));
		
	
	}

}
