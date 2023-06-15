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
public class VoteRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	////********** EN COURS DE REALISATION *******
	public List<Object> listVotes()
	{
		String uri = "http://127.0.0.1:8084/votes";
		RestTemplate restTemplate = new RestTemplate();
		Object[] lesVotes = restTemplate.getForObject(uri, Object[].class);
		return Arrays.asList(lesVotes);
		
	}

}
