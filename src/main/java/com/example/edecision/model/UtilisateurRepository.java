package com.example.edecision.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.edecision.model.Entity.Utilisateur;

@Transactional
@Repository
public class UtilisateurRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	//Récupération de la liste des utilisateurs
	public List<Utilisateur> listUtilisateurs()
	{
		String uri = "http://127.0.0.1:8085/utilisateurs";
		RestTemplate restTemplate = new RestTemplate();
		Utilisateur[] lesUtilisateurs = restTemplate.getForObject(uri, Utilisateur[].class);
		return Arrays.asList(lesUtilisateurs);
		
	}
	
	//Ajout d'un utilisateur
	public ResponseEntity<String> ajoutUtilisateur(Utilisateur unUtilisateur)
	{
		String uri = "http://127.0.0.1:8085/utilisateur";
		RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> resultat = restTemplate
	.postForEntity(uri , unUtilisateur, String.class);
		return resultat;
		
	}
	
	//Suppression d'un utilisateur
	public ResponseEntity<String> deleteUtilisateur(int id)
	{
		String uri = "http://127.0.0.1:8085/utilisateur/";
		RestTemplate restTemplate = new RestTemplate();
	String test = "";
	ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
	return new ResponseEntity<>("Suppression ok " + resultat.getBody().substring(21)+"", HttpStatus.BAD_REQUEST);
		//return "Suppression ok " + resultat.getBody().substring(21);
		
	}
	
	//Récupération d'un id utilisateur en fonction de son id
	public int getIdUtilisateurByIdAuthent(int idAuthent)
	{
		String uri = "http://127.0.0.1:8085/utilisateur/getId/";
		RestTemplate restTemplate = new RestTemplate();
	    int idUtilisateur = restTemplate.getForObject(uri + idAuthent, Integer.class);
		return idUtilisateur;
		
	}
	
	//Récupération d'un utilisateur en fonction de son id
	public Utilisateur getutilisateurById(int idUser)
	{
		String uri = "http://127.0.0.1:8085/utilisateur/";
		RestTemplate restTemplate = new RestTemplate();
	    Utilisateur unUtilisateur = restTemplate.getForObject(uri + idUser, Utilisateur.class);
		return unUtilisateur;
		
	}

}
