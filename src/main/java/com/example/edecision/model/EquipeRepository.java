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
public class EquipeRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object> listEquipes()
	{
		String uri = "http://127.0.0.1:8083/equipes";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesEquipes = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesEquipes);
		
	}

}
