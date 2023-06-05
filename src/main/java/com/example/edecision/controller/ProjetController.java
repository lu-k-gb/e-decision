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
import com.example.edecision.model.Projet;
import com.example.edecision.model.Utilisateur;
import com.example.edecision.service.JwtUserDetailsService;
import com.example.edecision.service.ProjetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class ProjetController {
	
	@Autowired
	private ProjetService projetService;
	
	@GetMapping(value = "/projets")
	public List<Object> getProjets() {
		List<Object> lesProjets = projetService.listProjets(); 
		return lesProjets;
	}
	
	@PostMapping("projet/add")
	public String ajoutProjet(@RequestBody Projet unprojet)
	{
		String result;
		result = projetService.ajoutProjet(unprojet);
		return result;
	}
	
	@DeleteMapping("projet/delete/{id}")
	public String delete(@PathVariable("id") int id)
	{
		String result;
		result = projetService.deleteProjet(id);
		return result;
	}

}
