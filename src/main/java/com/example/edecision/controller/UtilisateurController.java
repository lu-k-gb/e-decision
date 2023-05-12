package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.service.EquipeService;
import com.example.edecision.service.UtilisateurService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService service;

	@RequestMapping(value = "/utilisateurs", method = RequestMethod.GET)
	public List<Object> getUtilisateurs() {
		List<Object> lesUtilisateurs = service.listUtilisateurs(); 
		return lesUtilisateurs;
	}

}
