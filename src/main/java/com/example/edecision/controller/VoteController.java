package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.service.VoteService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
//Annotation qui permet de spécifier à swagger que l'on va passer le token que l'on aura saisi pour l'authent swagger
@SecurityRequirement(name = "bearerAuth")
//Toutes les routes de ce controller appellent le micro-service vote
public class VoteController {
	
	@Autowired
	private VoteService service;
//En cours de mise en place
	@GetMapping(value = "/votes")
	public List<Object> getVotes() {
		List<Object> lesVotes = service.listVotes(); 
		return lesVotes;
	}

}
