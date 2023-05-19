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

@Transactional
@Repository
public class UtilisateurRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object> listUtilisateurs()
	{
		String uri = "http://127.0.0.1:8085/utilisateurs";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesUtilisateurs);
		
	}
	
	public String ajoutUtilisateur(Utilisateur unUtilisateur)
	{
		String uri = "http://127.0.0.1:8085/utilisateur";
		RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> resultat = restTemplate
	.postForEntity(uri , unUtilisateur, String.class);
//		Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
		return resultat.getBody().toString();
		
	}
	
	public String deleteUtilisateur(int id)
	{
		String uri = "http://127.0.0.1:8085/utilisateur/";
		RestTemplate restTemplate = new RestTemplate();
	String test = "";
	ResponseEntity<String> resultat = restTemplate.exchange(uri + id, HttpMethod.DELETE,new HttpEntity<String>(test ), String.class);
	 //restTemplate
	//.delete();
//		Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
		return "Suppression ok " + resultat.getBody().substring(21);
		
	}
	
	public int getIdUtilisateurByNumeroAuthent(int idAuthent)
	{
		String uri = "http://127.0.0.1:8085/utilisateur/getId/";
		RestTemplate restTemplate = new RestTemplate();
	    int idUtilisateur = restTemplate.getForObject(uri + idAuthent, Integer.class);
		return idUtilisateur;
		
	}

}
