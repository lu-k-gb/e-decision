package com.example.edecision.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
//	public String ajoutUtilisateur()
//	{
//		String uri = "http://127.0.0.1:8085/utilisateurs";
//		RestTemplate restTemplate = new RestTemplate();
//		Object[] lesUtilisateurs = restTemplate.getForObject(uri, Object[].class);
//		return Arrays.asList(lesUtilisateurs);
//		
//	}

}
