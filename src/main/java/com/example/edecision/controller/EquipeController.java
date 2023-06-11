package com.example.edecision.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.edecision.model.EquipeSimple;
import com.example.edecision.service.EquipeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class EquipeController {
	
	@Autowired
	private EquipeService service;

	@GetMapping(value = "/equipes")
	public List<Object> getEquipes() {
		List<Object> lesEquipes = service.listEquipes(); 
		return lesEquipes;
	}
	
	//Création d'une équipe
			@PostMapping("equipe")
			public String ajoutEquipe(@RequestBody EquipeSimple uneEquipe)
			{
				String result = service.createEquipe(uneEquipe);
				return result;
			}
			
			//Suppression d'une équipe
			@DeleteMapping("equipe/{numero}")
			public String delete(@PathVariable("id") int id)
			{
				String result = service.delete(id);
				return result;
			}
			
			//Nouvelle requete de modification d'un attribut d'une equipe en utilisant des request param et en retournant une chaine de caractère
			@PutMapping("equipe/{id}")
			public String addEquipier(@PathVariable("id") int id , @RequestBody List<Integer> lesCoequipiers)
			{
				String result = service.addEquipier(id, lesCoequipiers);
				return result;
			}

}

