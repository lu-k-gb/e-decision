package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.service.EquipeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class EquipeController {
	
	@Autowired
	private EquipeService service;

	@RequestMapping(value = "/equipes", method = RequestMethod.GET)
	public List<Object> getEquipes() {
		List<Object> lesEquipes = service.listEquipes(); 
		return lesEquipes;
	}

}

