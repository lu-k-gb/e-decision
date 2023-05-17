package com.example.edecision.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.edecision.config.JwtTokenUtil;
import com.example.edecision.model.JwtResponse;
import com.example.edecision.model.Utilisateur;
import com.example.edecision.model.AuthentUtilisateur;
import com.example.edecision.model.Authentification;
import com.example.edecision.service.JwtUserDetailsService;
import com.example.edecision.service.UtilisateurService;


@RestController
@CrossOrigin
public class AuthentificationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private UtilisateurService utilisateursService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Authentification authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getName(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	//Création d'un compte authentification
			@PostMapping("register")
			public ResponseEntity<String> ajoutAuthentification(@RequestBody AuthentUtilisateur unUtilisateurComplet)
			{
				ResponseEntity<String> result;
				Authentification uneAuthentification = new Authentification();
				uneAuthentification.setName(unUtilisateurComplet.getName());
				uneAuthentification.setPassword(unUtilisateurComplet.getPassword());
				Utilisateur unutilisateur = new Utilisateur();
				unutilisateur.setNom(unUtilisateurComplet.getNom());
				unutilisateur.setPrenom(unUtilisateurComplet.getPrenom());
				unutilisateur.setAdresseMail(unUtilisateurComplet.getAdresseMail());
				result = userDetailsService.createAuthentification(uneAuthentification);
				
				if (result.getStatusCode() == HttpStatus.CREATED)
				{
					String resultat = result.getBody().substring(37);
					unutilisateur.setIdAuthentification(Integer.parseInt(resultat));
					String test = utilisateursService.ajoutUtilisateur(unutilisateur);
				}
				return result;
			}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}