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
public class PropositionRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object> listPropositions()
	{
		String uri = "http://127.0.0.1:8082/propositions";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesPropositions = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesPropositions);
		
	}

}
