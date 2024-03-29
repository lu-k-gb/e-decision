package com.example.edecision.controller;


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
import com.example.edecision.model.Entity.AuthentUtilisateur;
import com.example.edecision.model.Entity.Authentification;
import com.example.edecision.model.Entity.Utilisateur;
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
	private UtilisateurService utilisateursService;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	

	//Requete permettant de s'authentifier et de récupérer un token
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Authentification authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getName(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	//Création d'un compte authentification + utilisateur (appel au microservice utilisateur)
		@PostMapping("register")
		public ResponseEntity<String> ajoutAuthentification(@RequestBody AuthentUtilisateur unUtilisateurComplet)
		{
			ResponseEntity<String> result;
			ResponseEntity<String> test;
			//On crée un objet authentification à partir de l'objet contenant tous les champs
			Authentification uneAuthentification = new Authentification();
			uneAuthentification.setName(unUtilisateurComplet.getName());
			uneAuthentification.setPassword(unUtilisateurComplet.getPassword());
			//On crée un objet utilisateur à partir de l'objet contenant tous les champs
			Utilisateur unutilisateur = new Utilisateur();
			unutilisateur.setNom(unUtilisateurComplet.getNom());
			unutilisateur.setPrenom(unUtilisateurComplet.getPrenom());
			unutilisateur.setAdresseMail(unUtilisateurComplet.getAdresseMail());
			//On crée l'authentification
			result = userDetailsService.createAuthentification(uneAuthentification);
			
			//Si la création de l'authentification est faite
			if (result.getStatusCode() == HttpStatus.CREATED)
			{
				//On recupère le code de l'authenfication que l'on vient de créer
				String resultat = result.getBody().substring(37);
				//On l'ajoute ensuite à l'objet utilisateur
				unutilisateur.setIdAuthentification(Integer.parseInt(resultat));
				//On lance la création d'un utilisateur
				test = utilisateursService.ajoutUtilisateur(unutilisateur);
			}
			return result;
		}
	
	
		//Méthode appelée pour vérifier l'authentification
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