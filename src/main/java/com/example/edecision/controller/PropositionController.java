package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.model.Proposition;
import com.example.edecision.model.PropositionComplex;
import com.example.edecision.model.PropositionSample;
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
	//Création d'une propositionSimple
    @PostMapping("proposition")
    public String ajoutProposition(@RequestBody PropositionSample uneProposition)
    {
        String result = service.createProposition(uneProposition);
        return result;
    }
  //Création d'une propositionComplex
    @PostMapping("proposition/complexe")
    public String ajoutPropositionEscaladeOuAmendement(@RequestBody PropositionComplex uneProposition)
    {
        String result = service.createPropositionEscaladeOuAmendement(uneProposition);
        return result;
    }
    //Retirer une proposition
    @DeleteMapping("proposition/retirer/{id}")
	public String retirerProposition(@PathVariable("id") int id)
	{
		String result;
		result = service.retirerProposition(id);
		return result;
	}

}
