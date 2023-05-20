package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.service.PropositionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class PropositionController {
	
	@Autowired
	private PropositionService service;

	@GetMapping(value = "/propositions")
	public List<Object> getPropositions() {
		List<Object> lesPropositions = service.listPropositions(); 
		return lesPropositions;
	}

}
