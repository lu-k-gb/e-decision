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
public class ProjetRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object> listProjets()
	{
		String uri = "http://127.0.0.1:8081/projets";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesProjets = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesProjets);
		
	}

}
