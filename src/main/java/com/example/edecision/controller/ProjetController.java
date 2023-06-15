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

import com.example.edecision.model.Entity.AuthentUtilisateur;
import com.example.edecision.model.Entity.Authentification;
import com.example.edecision.model.Entity.Projet;
import com.example.edecision.model.Entity.Utilisateur;
import com.example.edecision.service.JwtUserDetailsService;
import com.example.edecision.service.ProjetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
//Annotation qui permet de spécifier à swagger que l'on va passer le token que l'on aura saisi pour l'authent swagger
@SecurityRequirement(name = "bearerAuth")
//Toutes les routes de ce controller appellent le micro-service projet
public class ProjetController {
	
	@Autowired
	private ProjetService projetService;
	
	//Récupération de tous les projets
	@GetMapping(value = "/projets")
	public List<Projet> getProjets() {
		List<Projet> lesProjets = projetService.listProjets(); 
		return lesProjets;
	}
	
	//Ajout d'un projet
	@PostMapping("projet/add")
	public String ajoutProjet(@RequestBody Projet unprojet)
	{
		String result;
		result = projetService.ajoutProjet(unprojet);
		return result;
	}
	
	//Suppression d'un projet
	@DeleteMapping("projet/delete/{id}")
	public String delete(@PathVariable("id") int id)
	{
		String result;
		result = projetService.deleteProjet(id);
		return result;
	}

}
