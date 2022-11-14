package com.example.edecision.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.edecision.model.Utilisateur;
import com.example.edecision.model.UtilisateurRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Utilisateur user = repo.getUserByName(username);
		 if (user != null)
		 {
		 return new User(user.getName(), user.getPassword(), new ArrayList<>());
		 }
//		if ("Lucaxel".equals(username)) {
//			return new User("Lucaxel", "$2a$10$dtm6KfKQoHdgG4bXnY4qquK1CIwY1J0egW7Gho1ZP0W7204j5HAgC",
//					new ArrayList<>()); }
		 else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
