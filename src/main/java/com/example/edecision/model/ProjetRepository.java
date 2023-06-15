package com.example.edecision.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.edecision.model.Entity.Projet;

@Transactional
@Repository
public class ProjetRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	//Récupération de la liste de projet par utilisation du micro service projet
	public List<Projet> listProjets()
	{
		String uri = "http://127.0.0.1:8081/projets";
		RestTemplate restTemplate = new RestTemplate();
		Projet[] lesProjets = restTemplate.getForObject(uri, Projet[].class);
		return Arrays.asList(lesProjets);
		
	}
	
	//Ajout d'un projet
	public String ajoutProjet(Projet unProjet)
	{
		String uri = "http://127.0.0.1:8081/projet";
		RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> resultat = restTemplate
	.postForEntity(uri , unProjet, String.class);
		return resultat.getBody().toString();
	}
	
	//Suppression d'un projet
	public String deleteProjet(int id)
	{
		String uri = "http://127.0.0.1:8081/projet/";
		RestTemplate restTemplate = new RestTemplate();
		String test = "";
		ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
		return resultat.getBody();
	}
	
	//Mise à jour de l'état d'un projet
	public String updateEtat(int id)
	{
		String uri = "http://127.0.0.1:8081/projet/setEtat/";
		RestTemplate restTemplate = new RestTemplate();
		String test = "";
		ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.PATCH,new HttpEntity<String>(test ), String.class);
		return "Modification ok " + resultat.getBody().substring(21);
	}
	
	//Récupération d'un projet en fonction de son id
	public Projet getProjetById(int idProjet)
	{
		String uri = "http://127.0.0.1:8081/projet/";
		RestTemplate restTemplate = new RestTemplate();
		Projet unProjet = restTemplate.getForObject(uri + idProjet, Projet.class);
		return unProjet;
		
	}

}
