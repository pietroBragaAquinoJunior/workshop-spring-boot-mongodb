package com.pietrobraga.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pietrobraga.workshopmongo.domain.Post;
import com.pietrobraga.workshopmongo.domain.User;
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
		
		Post p1 = new Post(null,"Partiu viagem!", sdf.parse("21/03/2018"), "Vou viajar para São Paulo, Abraços!", maria);
		Post p2 = new Post(null,"Hora de estudar!", sdf.parse("23/03/2018"), "Adoro estudar programação!", maria);
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		postRepository.saveAll(Arrays.asList(p1,p2));
		
	}

}
