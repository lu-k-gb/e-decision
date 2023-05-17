package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.model.AuthentUtilisateur;
import com.example.edecision.model.Authentification;
import com.example.edecision.model.Utilisateur;
import com.example.edecision.service.EquipeService;
import com.example.edecision.service.JwtUserDetailsService;
import com.example.edecision.service.UtilisateurService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateursService;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping(value = "/utilisateurs")
	public List<Object> getUtilisateurs() {
		List<Object> lesUtilisateurs = utilisateursService.listUtilisateurs(); 
		return lesUtilisateurs;
	}
	
	//Création d'un compte authentification
	@PostMapping("register")
	public ResponseEntity<String> ajoutAuthentification(@RequestBody AuthentUtilisateur unUtilisateurComplet)
	{
		ResponseEntity<String> result;
		String test;
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
			//(voir pour modifier le code pour rendre plus dynamique)
			String resultat = result.getBody().substring(37);
			unutilisateur.setIdAuthentification(Integer.parseInt(resultat));
			//On lance la création d'un utilisateur
			test = utilisateursService.ajoutUtilisateur(unutilisateur);
		}
		return result;
	}
	
	//Suppression d'un utilisateur + authentification
	@DeleteMapping("user/delete/{numero}")
	public ResponseEntity<String> delete(@PathVariable("numero") int numero)
	{
		ResponseEntity<String> result;
	    result = userDetailsService.deleteAuthentification(numero);
	    if (result.getStatusCode() == HttpStatus.OK)
		{
			String resultat = result.getBody().substring(43);
			//unutilisateur.setIdAuthentification(Integer.parseInt(resultat));
			String test = utilisateursService.deleteUtilisateur(Integer.parseInt(resultat));
		}
		return result;
	}

}
