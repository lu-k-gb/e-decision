package com.example.edecision.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.edecision.model.AuthentificationRepository;
import com.example.edecision.model.Entity.Authentification;



@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthentificationRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Authentification user = repo.getUserByName(username);
		 if (user != null)
		 {
		 return new User(user.getName(), user.getPassword(), new ArrayList<>());
		 }
		 else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	//Création d'une Authentification
			public ResponseEntity<String> createAuthentification(Authentification uneAuthentification)
			{
				return this.repo.createAuthentification(uneAuthentification);
			}
			
			//Suppression d'une Authentification
			public ResponseEntity<String> deleteAuthentification(int id)
			{
				return this.repo.deleteAuthentification(id);
			}
}
